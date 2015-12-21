# [WikiHTML](http://julianmendez.github.io/wikihtml/)

[![Build Status](https://travis-ci.org/julianmendez/wikihtml.png?branch=master)](https://travis-ci.org/julianmendez/wikihtml)

**WikiHTML** is a Java library and executable standalone application that converts a file in wiki text format to an HTML file.


## Author
[Julian Mendez](http://lat.inf.tu-dresden.de/~mendez/)


## Licenses
[Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt), [GNU Lesser General Public License version 3](http://www.gnu.org/licenses/lgpl-3.0.txt)


## Source code

To checkout and compile the project, use:
```
$ git clone https://github.com/julianmendez/wikihtml.git
$ cd wikihtml
$ mvn clean install
```
The created executable library, its sources, and its Javadoc will be in `wikihtml/target`.

To compile the project offline, first download the dependencies:
```
$ mvn dependency:go-offline
```
and once offline, use:
```
$ mvn --offline clean install
```

The bundles uploaded to [Sonatype](https://oss.sonatype.org/) are created with:
```
$ mvn clean install -DperformRelease=true
```
and then:
```
$ cd wikihtml/target
$ jar -cf bundle.jar wikihtml-*
```

The version number is updated with:
```
$ mvn versions:set -DnewVersion=NEW_VERSION
```
where *NEW_VERSION* is the new version.


## Contact

In case you need more information, please contact @julianmendez .



