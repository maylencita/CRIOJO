#!/usr/bin/env bash

awk '
BEGIN {
        addresse = "unknown"
        command = ""
    }

    {
        if($1 == "//file") {
            addresse = $2".program"
            print "touch result/" addresse
            command = "echo \"// this is a script generated from criojo tools\" > " "result/" addresse
        }
        else {
            gsub(/\"/, "\\\"", $0)
            command = "echo \""  $0 "\" >> " "result/" addresse
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