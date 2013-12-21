#!/bin/bash

files=`ls *.html`

for i in ${files}; do 
  java -jar wikihtml.jar ${i}
done


