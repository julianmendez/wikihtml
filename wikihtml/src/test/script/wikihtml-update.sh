#!/bin/bash


files=`ls *.html`

for i in ${files}; do 
  java -jar wikihtml-0.2.0-SNAPSHOT.jar ${i}
done


