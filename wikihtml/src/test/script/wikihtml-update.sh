#!/bin/bash

files=`ls *.html`

for i in ${files}; do 
  java -jar wikihtml-0.1.0.jar ${i}
done


