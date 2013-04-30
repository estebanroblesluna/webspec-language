BASEDIR=$(dirname $0)

mysql -u root < $BASEDIR/common.sql
mysql -u root < $BASEDIR/sampleData.sql
mysql -u root < $BASEDIR/playgroundSchema.sql