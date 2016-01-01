# [WikiHTML](http://julianmendez.github.io/wikihtml/)

[![Build Status](https://travis-ci.org/julianmendez/wikihtml.png?branch=master)](https://travis-ci.org/julianmendez/wikihtml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.tu-dresden.inf.lat.wikihtml/wikihtml/badge.svg)](http://search.maven.org/#search|ga|1|g%3A%22de.tu-dresden.inf.lat.wikihtml%22)


**WikiHTML** is a Java library and executable standalone application that converts a file in wiki text format to an HTML file.


## Download

* [executable JAR file](https://sourceforge.net/projects/latitude/files/wikihtml/0.1.0/wikihtml-0.1.0.jar/download)
* [The Central Repository](https://repo1.maven.org/maven2/de/tu-dresden/inf/lat/wikihtml/)
* as dependency:
```xml
<dependency>
  <groupId>de.tu-dresden.inf.lat.wikihtml</groupId>
  <artifactId>wikihtml</artifactId>
  <version>0.1.0</version>
</dependency>
```

## Description

Wiki markup, also wikitext or wikicode, is a markup language for wiki-based pages. It is a simplified human-friendly substitute of HTML. This library reads text written in this markup language and produces an HTML document. There are several "dialects" of wiki markup. This library implements a subset of the language used by the [MediaWiki](https://www.mediawiki.org/wiki/MediaWiki) software.


| wiki markup                   | HTML                 |
|:------------------------------|:---------------------|
| ```= heading 1 =```           | <h1>heading 1</h1>   |
| ```== heading 2 ==```         | <h2>heading 2</h2>   |
| ```=== heading 3 ===```       | <h3>heading 3</h3>   |
| ```==== heading 4 ====```     | <h4>heading 4</h4>   |
| ```===== heading 5 =====```   | <h5>heading 5</h5>   |
| ```====== heading 6 ======``` | <h6>heading 6</h6>   |



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


## Author
[Julian Mendez](http://lat.inf.tu-dresden.de/~mendez/)


## Licenses
[Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt), [GNU Lesser General Public License version 3](http://www.gnu.org/licenses/lgpl-3.0.txt)


## Release notes

See [release notes](http://github.com/julianmendez/wikihtml/blob/master/RELEASE-NOTES.md).


## Contact

In case you need more information, please contact @julianmendez .



