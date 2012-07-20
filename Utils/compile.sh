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
		completePath = "unknown"
		command = ""

		print "#!/usr/bin/env bash"
		print ""
	}
	
	{
        if($1 == "//file") {
            addresse = $2".scala"
            dossier = "servers/" $2
            completePath = dossier "/src/main/scala/application/"

            print "mkdir " dossier
            print "mkdir -p " dossier "/src/main/scala/application"
            print "mkdir -p " dossier "/src/test/scala/application"
            print "touch " dossier "/src/main/scala/application/" addresse
            command = "echo \"// this is a script generated from criojo tools\" > " completePath addresse
        }
        else if($1 == "//fileweb") {
            addresse = "AppLauncher.java"
            dossier = "servers/web"
            completePath = dossier "/src/main/java/application/"

            print "mkdir " dossier
            print "mkdir -p " completePath
            print "mkdir -p " dossier "/src/test/java/application"
            print "mkdir -p " dossier "/src/test/java/webapp/"
            print "cp -r " $2 " " dossier "/src/main/java/webapp/"
            print "touch " dossier "/src/main/java/application/" addresse
            command = "echo \"// this is a script generated from criojo tools\" > " completePath addresse
        }
        else {
            gsub(/\"/, "\\\"", $0)
            command = "echo \""  $0 "\" >> " completePath addresse
        }

        print command
    }

	END {
	}   
' > temp.temp

chmod +x temp.temp
./temp.temp

for folder in `ls servers/`; do
    if [ "$folder" != "web" ]; then
        eval "cp -r conf/cham servers/$folder/conf"
        eval "cp -r lib/cham servers/$folder/lib"
        eval "cp script/cham/pom.xml.template servers/$folder/pom.xml"
        eval "cp script/cham/run.sh servers/$folder/run.sh"
        eval "cp script/cham/stop.sh servers/$folder/stop.sh"
        eval "chmod +x servers/$folder/run.sh"
        eval "chmod +x servers/$folder/stop.sh"
    else
        eval "cp -r conf/web servers/$folder/conf"
        eval "cp -r lib/web servers/$folder/lib"
        eval "cp script/web/pom.xml.template servers/$folder/pom.xml"
        eval "cp script/web/run.sh servers/$folder/run.sh"
        eval "cp script/web/stop.sh servers/$folder/stop.sh"
        eval "chmod +x servers/$folder/run.sh"
        eval "chmod +x servers/$folder/stop.sh"
    fi
done

eval "cp conf/hosts.xml servers/hosts.xml"
eval "cp conf/mappings.xml servers/mappings.xml"
eval "cp script/clean.sh servers/clean.sh"
eval "cp lib/hq.zip servers/hq.zip"
eval "cp script/deploy.sh servers/deploy.sh"
eval "cp script/prepare.sh servers/prepare.sh"
eval "cp script/ssh_copy.sh servers/ssh_copy.sh"
eval "cp script/ssh_copy_directory.sh servers/ssh_copy_directory.sh"
eval "cp script/ssh_execute.sh servers/ssh_execute.sh"
eval "cp script/run-all.sh servers/run-all.sh"
eval "cp script/stop-all.sh servers/stop-all.sh"
eval "chmod +x servers/deploy.sh"
eval "chmod +x servers/run-all.sh"
eval "chmod +x servers/stop-all.sh"

if [ -f program.compiled.criojo ]
then
    rm program.compiled.criojo
fi

if [ -f temp.temp ]
then
    rm temp.temp
fi

if [ -f unknownunknown ]
then
    rm unknownunknown
fi
