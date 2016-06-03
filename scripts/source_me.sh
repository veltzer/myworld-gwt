#source this file!!!

# java stuff
export JAVA_VERSION=1.8.0_92
export JAVA_HOME=$(path_abs ~/install/jdk$JAVA_VERSION)
export PATH=$(path_prefix "$JAVA_HOME/bin" "$PATH")
export MANPATH=$(path_prefix "$JAVA_HOME/man" "$MANPATH")

# maven stuff
export MVN_VERSION=3.3.9 # my own version
export M2_HOME=$(path_abs ~/install/apache-maven-$MVN_VERSION)
export PATH=$(path_prefix "$M2_HOME/bin" "$PATH")

# ant stuff
export ANT_VERSION=1.9.7
export ANT_HOME=$(path_abs ~/install/apache-ant-$ANT_VERSION)
export PATH=$(path_prefix "$ANT_HOME/bin" "$PATH")
# This is needed so that secure connections to apache tomcat will succeed
# We are no longer using this instead we are importing our certificate to the cacerts
# store of java directly
# export ANT_OPTS="-Djavax.net.ssl.trustStore=/home/mark/.keystore -Djavax.net.ssl.trustStorePassword=PR0rV7320u"

# ivy stuff
export IVY_VERSION=2.4.0
export IVY_HOME=$(path_abs ~/install/apache-ivy-$IVY_VERSION)

# gwt stuff
export GWT_VERSION=2.7.0
export GWT_HOME=$(path_abs ~/install/gwt-$GWT_VERSION)

# gxt stuff
export GXT_VERSION=4.0.0-gpl
export GXT_HOME=$(path_abs ~/install/gxt-$GXT_VERSION)

# tomcat stuff
export TOMCAT_VERSION=8.0.35
export TOMCAT_HOME=$(path_abs ~/install/apache-tomcat-$TOMCAT_VERSION)
export PATH=$(path_prefix "$TOMCAT_HOME/bin" "$PATH")
