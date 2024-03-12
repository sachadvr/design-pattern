rm *.csv

./exec.sh insert -s tmp-4548038250487246284.csv --done "Your collective dating record reads like a who’s who of human crap."
./exec.sh insert -s tmp-4548038250487246284.csv "No! No, Joey! U-N-I-sex."
./exec.sh insert -s tmp-6728962803849607679.csv --done "\"It works on my machine\" always holds true for Chuck Norris."
./exec.sh migrate -s tmp-4548038250487246284.csv -o tmp-6728962803849607679.csv
./exec.sh list -s tmp-6728962803849607679.csv
