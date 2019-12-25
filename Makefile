export MODEL_BASENAME=taskmarket
export REPO_NAME=TaskMarket
export GITHUB_ORGANIZATION=kode-konveyor
export CONSISTENCY_INPUTS=taskmarket.rich shippable/behaviours.xml
LANGUAGE=java
BEFORE_ALL=runapache

include /usr/local/toolchain/rules.java

runapache:
	tools/runApache
	touch runapache

delink:
	zenta-xslt-runner -xsl:xslt/delink.xslt -s:$(MODEL_BASENAME).zenta -o:modelparts/$(MODEL_BASENAME).zentapart -im:delink

$(MODEL_BASENAME).zenta:
	zenta-xslt-runner -xsl:xslt/delink.xslt -o:$(MODEL_BASENAME).zenta -s:modelparts/$(MODEL_BASENAME).zentapart -im:link

