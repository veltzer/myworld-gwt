#source this file!!!

# java stuff
export JAVA_VERSION=1.7.0_80
export JAVA_HOME=$(abs_path ~/install/jdk$JAVA_VERSION)
export PATH=$(path_prefix "$JAVA_HOME/bin" "$PATH")
export MANPATH=$(path_prefix "$JAVA_HOME/man" "$MANPATH")

# maven stuff
export MVN_VERSION=3.3.3 # my own version
export M2_HOME=$(abs_path ~/install/apache-maven-$MVN_VERSION)
export PATH=$(path_prefix "$M2_HOME/bin" "$PATH")

# ant stuff
export ANT_VERSION=1.9.5
export ANT_HOME=$(abs_path ~/install/apache-ant-$ANT_VERSION)
export PATH=$(path_prefix "$ANT_HOME/bin" "$PATH")

# ivy stuff
export IVY_VERSION=2.4.0
export IVY_HOME=$(abs_path ~/install/apache-ivy-$IVY_VERSION)

# gwt stuff
export GWT_VERSION=2.7.0
export GWT_HOME=$(abs_path ~/install/gwt-$GWT_VERSION)

# gxt stuff
export GXT_VERSION=3.1.1-gpl
export GXT_HOME=$(abs_path ~/install/gxt-$GXT_VERSION)

# tomcat stuff
export TOMCAT_VERSION=8.0.23
export TOMCAT_HOME=$(abs_path ~/install/apache-tomcat-$TOMCAT_VERSION)
export PATH=$(path_prefix "$TOMCAT_HOME/bin" "$PATH")

