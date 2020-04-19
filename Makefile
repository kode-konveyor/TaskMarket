export MODEL_BASENAME=taskmarket
export REPO_NAME=TaskMarket
export GITHUB_ORGANIZATION=kode-konveyor
export CONSISTENCY_INPUTS=taskmarket.rich shippable/behaviours.xml
LANGUAGE=java
BEFORE_ALL=checksource runapache

include /usr/local/toolchain/rules.java

checksource:
	if git grep "" :^Makefile; then echo windows delimiters used, failing build; exit 1 ;fi
runapache:
	-tools/killApache
	tools/runApache
	touch runapache

delink:
	mkdir -p modelparts/metamodel
	zenta-xslt-runner -xsl:xslt/delink.xslt -s:$(MODEL_BASENAME).zenta -o:modelparts/$(MODEL_BASENAME).zentapart -im:delink

$(MODEL_BASENAME).zenta:
	zenta-xslt-runner -xsl:xslt/delink.xslt  -s:.zentasources -im:prepare|sed 's/<.*>//'|bash
	zenta-xslt-runner -xsl:xslt/delink.xslt -o:$(MODEL_BASENAME).zenta -s:modelparts/$(MODEL_BASENAME).zentapart -im:link

