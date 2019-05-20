# TEXT-SEARCH-ENGINE
The​ ​exercise​ ​is​ ​to​ ​write​ ​a​ ​command​ ​line​ ​driven​ ​text​ ​search​ ​engine.

# GOAL
The​ ​exercise​ ​is​ ​to​ ​write​ ​a​ ​command​ ​line​ ​driven​ ​text​ ​search​ ​engine.
This​ ​should​ ​read​ ​all​ ​the​ ​text​ ​files​ ​in​ ​the​ ​given​ ​directory,​
​building​ ​an​ i​​ n​ ​memory​​ ​representation​ ​of​ ​the files​ ​and​ ​their​ ​contents,​
​and​ ​then​ ​give​ ​a​ ​command​ ​prompt​ ​at​ ​which​ ​interactive​ ​searches​ ​can​ ​be performed.

# BUILD
The project contains Maven config for testing and packaging the application.

# RUN
java -jar cl-text-search-engine-1.0.0.jar /path/to/directory/containing/text/files

:quit to stop.

test folder available here: src/test/resources

# NEXT STEPS
change search algorithm

manage corner cases (i.e. empty repository, empty file)

throw proprietary exceptions

decorates com.company.searcheng.text.cl.TextSearchEngine with console output
