rm *.csv

./exec.sh insert -s tmp--6639512648690044190.csv --done "If you’re going to call me names, I would prefer Ross, the Divorce Force. It’s just cooler."
./exec.sh insert -s tmp--6639512648690044190.csv "You know what’s weird? Donald Duck never wore pants. But whenever he’s getting out of the shower, he always puts a towel around his waist. I mean, what is that about?"
./exec.sh insert -s tmp--6639512648690044190.csv --done "\"It works on my machine\" always holds true for Chuck Norris."
./exec.sh insert -s tmp-2296845861594962764.csv --done "How you doin’?"
./exec.sh insert -s tmp-2296845861594962764.csv "\"It works on my machine\" always holds true for Chuck Norris."
./exec.sh migrate -s tmp--6639512648690044190.csv -o tmp-2296845861594962764.csv
./exec.sh list -s tmp-2296845861594962764.csv --done

