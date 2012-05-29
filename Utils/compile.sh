#!/usr/bin/env bash



if [ -d servers ]
then
    rm -r servers
fi

mkdir servers

#cat program.compiled.criojo 
java -jar compiler/CriojoCompiler.jar $1 | awk '
	BEGIN {
		dossier = "."
		addresse = "unknown"
		command = ""
	}
	
	{
		if($1 == "//file") {
			addresse = $2".program"
			dossier = "servers/" $2
			
			print "mkdir " dossier
			print "touch " dossier "/" addresse
			command = "echo \"// this is a script generated from criojo tools\" > " dossier "/" addresse
		}
		else {
			gsub(/\"/, "\\\"", $0)
			command = "echo \""  $0 "\" >> " dossier "/" addresse
		}
	
		print command
	}
	END {
	}   
' > temp.temp

old_IFS=$IFS     # sauvegarde du séparateur de champ  
IFS=$'\n'  
for line in $(cat temp.temp)
do
    a=$line
    eval "$a"
done
IFS=$old_IFS     # rétablissement du séparateur de champ par défaut

for folder in `ls servers/`; do
    eval "cp -r conf servers/$folder/conf"
    eval "cp script/run.sh servers/$folder/run.sh"
    eval "chmod +x servers/$folder/run.sh"
done

eval "cp conf/hosts.xml servers/hosts.xml"
eval "cp script/deploy.sh servers/deploy.sh"
eval "cp script/run-all.sh servers/run-all.sh"
eval "cp script/ssh_hack.sh servers/ssh_hack.sh"
eval "chmod +x servers/deploy.sh"
eval "chmod +x servers/run-all.sh"

if [ -f program.compiled.criojo ]
then
    rm program.compiled.criojo
fi

if [ -f temp.temp ]
then
    rm temp.temp
fi
