1. Install in your local repo oracle jdbc driver jar, because it is not publicly accessible.
first download oracle driver from here http://www.oracle.com/technetwork/apps-tech/jdbc-112010-090769.html
then install it in local repo
mvn install:install-file  -Dfile=path-to-your-artifact-jar -DgroupId=your.groupId -DartifactId=your-artifactId -Dversion=version -Dpackaging=jar
for me it is:
mvn install:install-file  -Dfile=D:\frameworks\oracle\ojdbc6.jar -DgroupId=ojdbc -DartifactId=ojdbc -Dversion=14 -Dpackaging=jar

