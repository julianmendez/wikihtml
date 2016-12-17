# [WikiHTML](https://julianmendez.github.io/wikihtml/)

[![Build Status](https://travis-ci.org/julianmendez/wikihtml.png?branch=master)](https://travis-ci.org/julianmendez/wikihtml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.tu-dresden.inf.lat.wikihtml/wikihtml/badge.svg)](http://search.maven.org/#search|ga|1|g%3A%22de.tu-dresden.inf.lat.wikihtml%22)


**WikiHTML** is a Java library and executable standalone application that converts a document in wiki text format to an HTML document.


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

## Use

It can be used as a Java library or from the command line. For example, use:

```
java -jar wikihtml-0.1.0.jar inputfile.text outputfile.html
```

to create a new HTML file from the command line, and use

```
java -jar wikihtml-0.1.0.jar inputoutputfile.html
```

to just update an HTML file with embedded wiki text.


## Description

Wiki markup, also wikitext or wikicode, is a markup language for wiki-based pages. It is a simplified human-friendly substitute of HTML. This library reads text written in this markup language and produces an HTML document. There are several "dialects" of wiki markup. This library implements a subset of the language used by the [MediaWiki](https://www.mediawiki.org/wiki/MediaWiki) software.

The application generates the HTML document with the original wiki markup source code inside. Technically, the source code will be between: `<!--begin_wiki_text` and `end_wiki_text-->`. This allows to update an HTML file using the source in the same file. 

This could be useful, for example, when maintaining documentation of a project. The files can be easily edited using a text editor, but after processing them with this library, they can be viewed with a browser.

When using only the wiki formatting, the produced document is an [XHTML 1.1](http://www.w3.org/TR/xhtml11/) document.


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

The double square brackets ([[ ]]) are rendered as local links.


#### Tables

This wiki text:

```html
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

```json
{||; border="1"
4;9;2
3;5;7
8;1;6
||}
```

* using comma:

```markdown
{||, border="1"
4,9,2
3,5,7
8,1,6
||}
```

* using tabs:

```xml
{|| border="1"
4  9  2
3  5  7
8  1  6
||}
```


#### nowiki

The pair of tags `<nowiki>`...`</nowiki>` is used to mark text without using the wiki formatting. For example:
`<nowiki>'''</nowiki>non-bold<nowiki>'''</nowiki>` is not in bold.


#### Variables

The following MediaWiki variables are implemented:

| name                              | example          | meaning                                                                |
|:----------------------------------|:-----------------|:-----------------------------------------------------------------------|
|<tt>&#x7B;{CURRENTDAY}}</tt>       |	`1`              | Displays the current day in numeric form.                              |
|<tt>&#x7B;{CURRENTDAY2}}</tt>      |	`01`            | Same as <tt>&#x7B;{CURRENTDAY}}</tt>, but with leading zero (01 .. 31). |
|<tt>&#x7B;{CURRENTDAYNAME}}</tt>   |	`Friday`         | Name of the day in the language of the project or English.             |
|<tt>&#x7B;{CURRENTDOW}}</tt>       |	`5`  | Same as <tt>&#x7B;{CURRENTDAYNAME}}</tt>, but as a number (0=Sunday, 1=Monday...). |
|<tt>&#x7B;{CURRENTMONTH}}</tt>     |	`01`             | The number 01 .. 12 of the month.                                      |
|<tt>&#x7B;{CURRENTMONTHABBREV}}</tt>| `Jan`   | Same as <tt>&#x7B;{CURRENTMONTH}}</tt>, but in abbreviated form as Jan .. Dec. |
|<tt>&#x7B;{CURRENTMONTHNAME}}</tt> |`January` | Same as <tt>&#x7B;{CURRENTMONTH}}</tt>, but in named form January .. December. |
|<tt>&#x7B;{CURRENTTIME}}</tt>      |	`16:03`          | The current time (00:00 .. 23:59).                                     |
|<tt>&#x7B;{CURRENTHOUR}}</tt>      |	`16`             | The current hour (00 .. 23).                                           |
|<tt>&#x7B;{CURRENTWEEK}}</tt>      |	`1`        | Number of the current week (1-53) according to ISO 8601 with no leading zero.|
|<tt>&#x7B;{CURRENTYEAR}}</tt>      |	`2016`           | Returns the current year.                                              |
|<tt>&#x7B;{CURRENTTIMESTAMP}}</tt> |	`20160101160345` | ISO 8601 time stamp                                                    |

In addition, the <tt>&#x7B;{LOCAL...}}</tt> variables are also implemented:<tt>&#x7B;{LOCALDAY}}</tt>, <tt>&#x7B;{LOCALDAY2}}</tt>, ... , <tt>&#x7B;{LOCALTIMESTAMP}}</tt>. For example, in UTC+1 <tt>&#x7B;{CURRENTTIMESTAMP}}</tt> returns `20160101160345`, while <tt>&#x7B;{LOCALTIMESTAMP}}</tt> returns `20160101170345`.  	


#### HTML

HTML code can also be inserted directly. For example:
`<b>bold</b>` is the same as `'''bold'''`, and `&lambda;` is rendered &lambda;.


## Example

The file [mupuzzle.text](https://github.com/julianmendez/wikihtml/blob/master/wikihtml/src/test/resources/mupuzzle.text) has the following wiki text:

```
== MIU system  ==
(see [https://en.wikipedia.org/wiki/MU_puzzle MU puzzle])

# ''x''I &rarr; ''x''IU
# M''x'' &rarr; M''xx''
# ''x''III''y'' &rarr; ''x''U''y'' 
# ''x''UU''y'' &rarr; ''xy''
```

and is translated to the following HTML document:

```html
<?xml version="1.0" encoding="utf-8"?>
<!--begin_wiki_text
== MIU system  ==
(see [https://en.wikipedia.org/wiki/MU_puzzle MU puzzle])

# ''x''I &rarr; ''x''IU
# M''x'' &rarr; M''xx''
# ''x''III''y'' &rarr; ''x''U''y'' 
# ''x''UU''y'' &rarr; ''xy''

end_wiki_text-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title></title>
</head>
<body>
  <div>


<h2> MIU system  </h2>
 (see <a href="https://en.wikipedia.org/wiki/MU_puzzle">MU puzzle</a>)<br />

<ol>
<li> <i>x</i>I &rarr; <i>x</i>IU</li>
<li> M<i>x</i> &rarr; M<i>xx</i></li>
<li> <i>x</i>III<i>y</i> &rarr; <i>x</i>U<i>y</i> </li>
<li> <i>x</i>UU<i>y</i> &rarr; <i>xy</i></li>
</ol>
<br />



  </div>
</body>
</html>
```

The file [example.text](https://github.com/julianmendez/wikihtml/blob/master/wikihtml/src/test/resources/example.text) has more examples.


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


## Architecture

The library reads a wiki text and creates a `WikiDocument`. 
It extracts the wiki text from the given input and processes it line by line.

Each line is transformed into a `ConversionToken`. Each token is processed by a pipeline of objects where each one is a `Renderer`. Each renderer (`-Renderer`) processes each conversion token producing a list of conversion tokens. These are the input for the next renderer, if any. Some renderers are parameterized and grouped (`-GroupRenderer`). Some renderers process whole lines (in package `...line`) and some renderers process pieces of lines (in package `...part`).

For example, all variables are processed by `...part.DateVariableRenderer`, but the headings are processed by a group of renderers (`...line.HeadingGroupRenderer`) composed by 6 renderers (h1, h2, ..., h6), where each one is a `...line.HeadingRenderer`. 


## Author

[Julian Mendez](http://lat.inf.tu-dresden.de/~mendez/)


## Licenses

[Apache License Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt), [GNU Lesser General Public License version 3](https://www.gnu.org/licenses/lgpl-3.0.txt)


## Release notes

See [release notes](https://github.com/julianmendez/wikihtml/blob/master/RELEASE-NOTES.md).


## Contact

In case you need more information, please contact @julianmendez .



