PROJECTDIR=$1

if [ -z $PROJECTDIR ]; then
	echo "Usage: ./testAll.sh <project directory>"
	exit 0
fi

echo "Compiling..."
cd $PROJECTDIR && make &> /dev/null && cd -
echo "Done compiling!"

if [ ! -d results/ ]; then
	mkdir results
fi

for FILE_RAW in $(ls auto-tests/*.in);
do
	FILE=$(echo $FILE_RAW | grep -o -P "A-\d{2}-\d{2}-M-ok")
	echo Testing $FILE...
	java -Dimport=auto-tests/$FILE.import -Din=auto-tests/$FILE.in -Dout=results/$FILE.outhyp -cp "/usr/share/java/po-uuilib.jar:$PROJECTDIR/sth-core/sth-core.jar:$PROJECTDIR/sth-app/sth-app.jar" sth.app.App	
	echo "Testing now the result:"
	diff -b auto-tests/expected/$FILE.out results/$FILE.outhyp
done	



echo "Done!"
