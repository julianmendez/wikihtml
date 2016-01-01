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


#### Sections

Sections are marked at the beginning of a line. The heading should be between a sequence of equals signs (=). Using more equals signs makes the heading smaller. For example:

| wiki markup                   | HTML                    |
|:------------------------------|:------------------------|
| `= heading 1 =`               | <h1>heading 1</h1>      |
| `== heading 2 ==`             | <h2>heading 2</h2>      |
| `=== heading 3 ===`           | <h3>heading 3</h3>      |
| `==== heading 4 ====`         | <h4>heading 4</h4>      |
| `===== heading 5 =====`       | <h5>heading 5</h5>      |
| `====== heading 6 ======`     | <h6>heading 6</h6>      |


#### Line breaks

A new line is marked with two new lines. For example,
```
Two lines
together
are not considered different lines.
```
is rendered
```
Two lines together are not considered different lines.
```
but:
```
One line.

Another line.
```
is rendered
```
One line.
Another line.
```


#### Indented text

Text can be indented using colons (:) at the beginning of the line. For example:
```
: item 1
: item 2
:: item 2.1
:: item 2.2
::: item 2.2.1
: item 3
```
produces:

```
   item 1
   item 2
     item 2.1
     item 2.2
       item 2.2.1
   item3
```



#### Unordered lists

Items in a list are marked with asterisks (*) at the beginning of the line. A subitem is marked with more asterisks. For example:
```
* item 1
* item 2
** item 2.1
** item 2.2
*** item 2.2.1
* item 3
```

is rendered as

* item 1
* item 2
 * item 2.1
 * item 2.2
   * item 2.2.1
* item 3


#### Ordered lists

Numbered items are marked with hash signs (#) at the beginning of the line. A subitem is marked with more hash signs. For example:
```
# item 1
# item 2
## item 2.1
## item 2.2
### item 2.2.1
# item 3
```

is rendered as

1. item 1
2. item 2
  1. item 2.1
  2. item 2.2
    1. item 2.2.1
3. item 3


#### Text formatting

The text can be formatted using apostrophes (') according to the following table:

| wiki markup                   | HTML                    |
|:------------------------------|:------------------------|
| `''italics''`                 | *italics*               | 
| `'''bold'''`                  | **bold**                | 
| `'''''bold italics'''''`      | ***bold italics***      | 


#### Links

Links can be marked with square backets ([ ]). For example:
`[http://www.wikipedia.org Wikipedia]` renders [Wikipedia](http://www.wikipedia.org).
If the brackets are omitted, the URI is shown directly. For example: `http://www.wikipedia.org` renders http://www.wikipedia.org .


#### Tables

This wiki text:
```
{| border="1"
| 4 || 9 || 2
|-
| 3 || 5 || 7
|-
| 8 || 1 || 6
|}
```

produces the following table:

<table boder="1">
<tr><td>4</td><td>9</td><td>2</td></tr>
<tr><td>3</td><td>5</td><td>7</td></tr>
<tr><td>8</td><td>1</td><td>6</td></tr>
</table>
 
(without the white and gray alternation of lines)

The following wiki text is not implemented in MediaWiki, but it also produces the same table:

* using semicolon:
```
{||; border="1"
4;9;2
3;5;7
8;1;6
||}
```

* using comma:
```
{||, border="1"
4,9,2
3,5,7
8,1,6
||}
```

* using tabs:
```
{|| border="1"
4  9  2
3  5  7
8  1  6
||}
```


#### nowiki

The `<nowiki>`...`</nowiki>` is used to mark text without using the wiki formatting. For example:
`<nowiki>'''</nowiki>non-bold<nowiki>'''</nowiki>` is not in bold.


#### Variables

The following MediaWiki variables are implemented:

| name                    | example          | meaning                                                                     |
|:------------------------|:-----------------|:----------------------------------------------------------------------------|
|`{{CURRENTDAY}}`         |	`1`              |	Displays the current day in numeric form.                                  |
|`{{CURRENTDAY2}}`        |	`01`             |	Same as `{{CURRENTDAY}}`, but with leading zero (01 .. 31).                |
|`{{CURRENTDAYNAME}}`     |	`Friday`         |	Name of the day in the language of the project or English.                 |
|`{{CURRENTDOW}}`         |	`5`              |	Same as `{{CURRENTDAYNAME}}`, but as a number (0=Sunday, 1=Monday...).     |
|`{{CURRENTMONTH}}`       |	`01`             |	The number 01 .. 12 of the month.                                          |
|`{{CURRENTMONTHABBREV}}` |	`Jan`            |	Same as `{{CURRENTMONTH}}`, but in abbreviated form as Jan .. Dec.         |
|`{{CURRENTMONTHNAME}}`   |	`January`        |	Same as `{{CURRENTMONTH}}`, but in named form January .. December.         |
|`{{CURRENTTIME}}`        |	`16:03`          |	The current time (00:00 .. 23:59).                                         |
|`{{CURRENTHOUR}}`        |	`16`             |	The current hour (00 .. 23).                                               |
|`{{CURRENTWEEK}}`        |	`1`            |	Number of the current week (1-53) according to ISO 8601 with no leading zero.|
|`{{CURRENTYEAR}}`        |	`2016`           |	Returns the current year.                                                  |
|`{{CURRENTTIMESTAMP}}`   |	`20160101160345` |	ISO 8601 time stamp                                                        |

In addition, the ``{{LOCAL...}}`` variables are also implemented:`{{LOCALDAY}}`, `{{LOCALDAY2}}`, ... , `{{LOCALTIMESTAMP}}`. For example, in UTC+1 `{{CURRENTTIMESTAMP}}` returns `20160101160345`, while `{{LOCALTIMESTAMP}}` returns `20160101170345`.  	


#### HTML

HTML code can also be inserted directly. For example:
`<b>bold</b>` is the same as `'''bold'''`, and `&lambda;` is rendered &lambda;.


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



