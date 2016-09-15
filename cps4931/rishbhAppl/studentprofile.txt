# @(#)cs.profile 1.8	99/03/26 SMI
UMASK=077
stty istrip

PATH=/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:/usr/ucb:/etc:/bin:.
export PATH

# export JAVA_HOME=/usr/java/latest
export  JAVA_HOME=/usr/lib/jvm/jre
# JAVA_HOME=/usr/lib/jvm/java-1.6.0
export ANT_HOME=/home/admin/tomcat/apache-ant-1.7.1
export PATH=$PATH:$JAVA_HOME/bin:$ANT_HOME/bin
                              
/bin/bash


#
# If possible, start the windows system
#
if [ "`tty`" = "/dev/console" ] ; then
	if [ "$TERM" = "sun" -o "$TERM" = "sun-color" -o "$TERM" = "AT386" ]
	then

		if [ ${OPENWINHOME:-""} = "" ] ; then
			OPENWINHOME=/usr/openwin
			export OPENWINHOME
		fi

		echo ""
		echo "Starting OpenWindows in 5 seconds (type Control-C to interrupt)"
		sleep 5
		echo ""
		$OPENWINHOME/bin/openwin

		clear		# get rid of annoying cursor rectangle
		exit		# logout after leaving windows system

	fi
fi

