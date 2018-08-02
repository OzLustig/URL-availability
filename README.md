C + JAVA application that checks the availability of Internet URLs.
The application should receive a file containing a list of URLs, and the number of processes we
want to use. After a successful run the application prints a numeric summary of the available
URLs, erroneous URLs and unknown (for URLs it failed to test).

Examples (number of OK/Error/Unknown might vary, even between runs​)
$ ./ex2
usage:
./ex2 FILENAME NUMBER_OF_PROCESSES
$ ./ex2 top10.txt 1
6 OK, 2 Error, 2 Unknown
$ ./ex2 top10.txt 2
7 OK, 1 Error, 2 Unknown
$ ./ex2 top10.txt 10
8 OK, 2 Error, 0 Unknown
