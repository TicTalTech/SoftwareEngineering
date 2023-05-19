package HW1.tests;

public class Boards200
{

    public static final String[] BOARDS = {
            "_",
            "_",
            "_ 1",
            "_ 1",
            "_|1",
            "1|_",
            "1 2 _",
            "_ 1 2",
            "1|_|2",
            "1|_|2",
            "1 2 _ 3",
            "1 2 3 _",
            "3 1|_ 2",
            "_ 3|2 1",
            "_|1|2|3",
            "_|1|2|3",
            "1 2 3 4 _",
            "_ 1 2 3 4",
            "1|2|_|3|4",
            "_|1|2|3|4",
            "1 2 3 4 5 _",
            "1 2 3 4 _ 5",
            "4 5 3|_ 1 2",
            "1 _ 5|3 2 4",
            "1 5|2 _|3 4",
            "3 _|1 4|5 2",
            "_|1|2|3|4|5",
            "_|1|2|3|4|5",
            "1 2 _ 3 4 5 6",
            "1 2 3 4 5 _ 6",
            "1|2|3|_|4|5|6",
            "1|2|_|3|4|5|6",
            "1 _ 2 3 4 5 6 7",
            "1 2 3 4 5 6 7 _",
            "2 3 _ 1|5 4 6 7",
            "1 3 5 _|2 7 4 6",
            "4 6|3 1|_ 5|7 2",
            "5 1|6 _|3 7|2 4",
            "1|2|3|4|_|5|6|7",
            "1|2|_|3|4|5|6|7",
            "1 2 3 4 5 6 7 8 _",
            "1 2 3 4 5 6 _ 7 8",
            "4 6 2|1 3 8|5 _ 7",
            "1 8 5|2 4 6|_ 7 3",
            "1|2|3|4|5|_|6|7|8",
            "1|_|2|3|4|5|6|7|8",
            "1 2 3 4 5 6 _ 7 8 9",
            "1 2 3 4 5 _ 6 7 8 9",
            "_ 3 5 8 2|9 6 4 1 7",
            "5 2 9 8 _|1 7 6 4 3",
            "3 1|2 _|7 4|6 8|5 9",
            "_ 6|9 4|3 5|8 1|2 7",
            "1|2|3|4|5|_|6|7|8|9",
            "1|2|3|4|_|5|6|7|8|9",
            "7 3 _ 9 6 4|2 11 5 10 8 1",
            "3 10 8 1 _ 5|2 6 7 9 4 11",
            "7 11 9 2|10 3 4 1|8 _ 6 5",
            "8 10 9 4|2 1 5 3|11 _ 7 6",
            "2 9 5|11 10 _|4 7 3|8 1 6",
            "_ 2 9|3 6 1|4 8 10|5 7 11",
            "10 8|7 2|1 5|11 6|9 4|3 _",
            "2 4|6 7|1 3|8 10|11 5|9 _",
            "2 11 3 8 1 9 13|12 7 5 6 4 10 _",
            "11 2 5 _ 13 3 4|8 10 9 1 6 12 7",
            "5 6|2 _|1 3|4 11|8 10|7 9|13 12",
            "9 2|4 8|13 1|10 5|_ 6|7 11|3 12",
            "9 12 6 10 7|_ 1 3 4 8|11 14 13 2 5",
            "14 5 12 8 10|11 2 _ 7 9|1 6 4 13 3",
            "7 4 12|1 2 8|9 6 11|13 10 5|14 _ 3",
            "10 9 12|6 4 _|14 11 1|2 7 5|13 8 3",
            "3 4 _ 8 7 10 14 12|2 9 5 1 6 13 15 11",
            "1 _ 6 8 12 10 9 15|3 11 5 13 4 7 14 2",
            "15 9 4 7|14 5 _ 2|13 12 6 8|3 10 11 1",
            "5 6 2 10|13 11 4 1|15 7 8 12|3 14 9 _",
            "10 7|_ 6|4 12|1 11|13 5|15 2|14 3|8 9",
            "8 9|_ 6|7 4|3 13|2 15|14 11|12 10|5 1",
            "1 7 4 13 17 12 8 16 6|3 11 9 2 10 5 14 _ 15",
            "14 10 13 2 6 15 11 3 16|_ 12 5 1 7 8 9 17 4",
            "13 10 9 12 15 11|7 3 17 16 1 _|5 8 2 6 4 14",
            "12 2 15 _ 3 11|6 8 9 14 4 5|7 13 1 16 10 17",
            "3 9 10|6 2 14|1 17 _|15 7 16|5 13 11|8 12 4",
            "15 7 13|_ 9 6|2 17 11|5 1 3|14 10 16|12 4 8",
            "2 6|11 5|1 _|10 8|3 14|4 17|16 13|15 7|9 12",
            "3 13|2 10|1 15|16 9|4 11|5 7|12 8|17 6|14 _",
            "13 2 8 15 10 12 14 18 17 16|4 11 _ 3 6 9 7 19 1 5",
            "1 3 12 2 17 5 _ 7 18 10|8 13 11 14 15 16 6 4 19 9",
            "1 15 19 3 9|14 16 13 17 7|6 _ 18 5 10|4 2 8 11 12",
            "17 19 8 1 2|3 5 9 14 13|15 4 11 12 6|_ 16 18 10 7",
            "8 9 14 2|10 15 1 3|11 5 6 4|13 18 12 _|17 7 19 16",
            "10 6 16 19|3 9 8 14|18 2 15 _|12 1 7 4|13 11 17 5",
            "1 14|6 7|3 2|_ 4|12 9|5 17|10 18|19 13|11 8|15 16",
            "3 11|5 _|15 16|2 8|6 9|12 1|17 10|19 4|13 18|14 7",
            "10 3 13 7 _ 19 16|17 18 4 15 6 14 20|2 9 11 8 1 5 12",
            "_ 4 2 5 16 8 19|9 20 18 3 14 7 15|12 1 6 11 13 10 17",
            "20 8 1|14 6 13|12 9 4|16 18 _|10 5 19|7 11 15|2 3 17",
            "17 18 8|2 _ 4|9 5 20|14 15 13|7 19 16|11 6 3|12 10 1",
            "4 17 7 2 10 12 21 16|19 1 11 23 8 14 13 5|18 _ 22 9 20 3 15 6",
            "15 12 17 10 _ 6 5 14|19 4 16 20 8 3 7 23|9 1 11 18 13 21 2 22",
            "20 18 1 7 2 19|3 22 8 5 21 9|10 17 11 4 14 23|16 12 15 13 6 _",
            "20 23 14 9 11 2|19 22 15 4 21 1|16 _ 13 7 10 12|3 18 17 8 5 6",
            "16 1 5 20|18 3 17 8|7 21 22 19|23 11 13 6|14 4 15 _|9 12 10 2",
            "2 15 20 23|3 11 6 7|4 19 10 8|22 9 _ 12|14 16 17 5|21 18 1 13",
            "15 18 12|17 23 10|8 9 7|21 13 5|_ 2 11|19 14 3|22 1 16|4 6 20",
            "_ 8 2|13 10 12|9 1 4|7 11 15|19 21 5|6 20 22|17 3 23|14 18 16",
            "16 2 6 19 20|12 1 4 3 22|23 8 9 10 13|24 21 14 _ 7|5 18 11 17 15",
            "23 22 12 9 13|19 _ 8 11 20|18 7 6 21 5|15 24 14 2 17|3 16 4 1 10",
            "1 4 12 21 3 19 11 6 26|16 23 13 14 5 7 _ 15 25|10 20 2 22 24 9 17 8 18",
            "21 2 25 1 17 7 8 19 26|5 9 11 16 23 18 12 _ 20|22 4 14 3 6 10 13 15 24",
            "25 14 2|6 5 12|17 18 11|7 16 8|1 9 22|24 10 21|13 _ 4|15 20 23|26 19 3",
            "9 15 17|25 4 7|8 11 1|12 14 3|2 26 5|10 6 23|13 19 _|16 24 21|20 18 22",
            "22 12 4 15 13 20 5|7 19 6 18 25 1 23|3 _ 10 9 2 27 26|21 11 8 24 17 16 14",
            "22 10 11 16 6 1 15|13 8 27 14 _ 5 26|23 17 7 12 3 19 24|25 21 2 20 4 9 18",
            "26 23 22 14|10 8 12 25|21 6 18 5|24 7 _ 15|17 3 2 19|20 11 13 1|27 9 16 4",
            "22 17 27 25|_ 24 1 12|23 21 18 16|7 2 20 8|15 5 14 3|19 4 26 10|6 11 9 13",
            "22 1 _ 6 4 8 23 29 5 19|10 12 24 2 11 25 20 16 17 18|13 7 21 15 14 3 26 28 9 27",
            "15 3 24 17 29 22 6 25 27 2|13 18 14 4 5 19 11 16 26 10|21 12 1 23 9 20 _ 28 7 8",
            "27 9 2 6 19 3|13 18 25 15 14 24|4 10 22 28 16 _|8 21 29 7 12 1|11 20 23 17 5 26",
            "14 27 24 6 5 21|23 8 19 11 4 1|12 29 16 25 18 26|20 22 15 10 28 7|_ 9 13 17 3 2",
            "14 20 23 2 8|6 10 5 27 22|_ 19 24 13 25|16 4 29 21 28|15 17 11 7 18|12 9 3 1 26",
            "7 15 1 23 2|21 5 18 14 8|24 _ 12 19 4|9 16 26 13 11|6 20 29 27 17|10 3 28 25 22",
            "25 9 14|2 _ 16|8 10 6|4 24 12|28 5 29|11 21 17|23 7 1|20 19 15|18 3 26|27 22 13",
            "9 19 4|27 10 12|1 2 28|29 13 11|21 5 _|18 20 7|17 25 16|24 14 6|22 26 3|8 23 15",
            "19 10 12 16 29 1 8 2|14 22 18 _ 31 17 23 5|30 24 15 28 25 3 13 26|20 9 7 4 6 11 27 21",
            "3 1 27 18 7 13 23 6|16 19 9 22 30 29 21 4|12 20 28 _ 5 15 31 25|24 14 17 8 26 2 11 10",
            "12 14 _ 5|29 24 26 25|3 31 6 27|20 23 4 11|7 8 10 21|13 28 30 18|1 19 9 17|16 22 15 2",
            "25 20 27 30|7 1 16 23|11 13 18 14|15 10 26 5|12 9 17 31|28 24 29 2|4 21 8 3|22 19 _ 6",
            "27 31 17 6 22 33 3|20 23 14 1 19 2 5|13 16 34 _ 4 10 15|7 25 8 29 9 11 21|28 30 12 26 32 24 18",
            "23 25 29 7 2 8 15|13 19 4 34 28 31 10|18 _ 5 1 16 21 33|12 24 17 6 30 14 11|9 3 26 22 32 20 27",
            "11 3 21 10 9|13 33 28 17 34|20 22 16 12 15|32 18 8 30 19|29 24 23 _ 1|31 26 6 5 27|4 14 2 25 7",
            "7 11 24 23 8|18 16 14 2 _|33 15 34 27 31|6 26 22 19 29|32 3 4 9 30|1 17 21 13 10|25 20 12 28 5",
            "27 28 18 3 12 6 31 17 30|20 2 14 10 4 11 7 9 24|_ 29 22 32 21 23 19 16 26|15 8 35 1 25 5 33 13 34",
            "13 32 12 27 9 _ 23 29 6|11 22 20 21 2 33 35 3 25|19 28 30 26 7 34 31 4 14|18 10 24 1 8 17 5 15 16",
            "27 22 12 17 1 30|6 15 14 26 29 16|5 11 2 8 10 19|23 20 13 3 4 28|35 32 33 34 21 _|7 9 24 25 31 18",
            "30 33 12 28 16 18|3 11 31 19 5 2|_ 27 24 32 26 29|10 8 7 1 13 6|14 25 35 15 20 23|34 17 4 22 9 21",
            "3 33 30 18|14 31 27 23|12 5 29 15|24 6 16 2|1 11 32 13|_ 35 21 28|25 9 4 22|20 17 26 10|34 7 19 8",
            "18 2 30 17|4 7 26 3|23 33 24 5|8 11 28 1|_ 14 21 12|20 19 10 15|25 16 13 31|32 6 27 29|22 9 34 35",
            "27 30 22 39 38 24 35 12 14 28|11 17 18 37 16 1 19 33 20 26|29 36 6 13 15 32 25 _ 5 7|21 4 3 34 31 9 10 8 2 23",
            "27 23 13 _ 4 29 5 16 12 36|37 1 25 22 26 7 28 10 34 9|2 17 11 24 6 20 39 32 35 19|33 31 30 18 38 15 3 14 21 8",
            "10 30 21 24 11 7 37 17|33 27 35 4 6 34 1 9|26 5 25 8 13 20 29 23|32 22 14 38 39 _ 18 36|12 28 19 2 15 16 3 31",
            "19 8 4 23 29 3 24 20|37 16 2 32 11 12 18 28|1 25 36 35 27 _ 9 13|21 33 26 22 7 5 14 17|30 38 39 31 6 10 15 34",
            "28 1 7 4 3|14 19 31 39 29|24 36 38 12 21|25 18 32 2 34|26 15 17 13 33|6 20 35 _ 27|8 23 16 22 5|11 30 37 10 9",
            "27 13 23 3 38|39 _ 37 33 20|12 7 10 24 5|35 36 15 22 29|19 32 26 11 21|9 1 17 31 18|4 8 34 14 6|25 16 2 28 30",
            "10 1 22 25|27 26 3 18|33 32 8 39|31 23 24 37|34 _ 13 4|20 9 16 14|7 35 5 17|30 12 11 28|6 21 15 38|36 29 2 19",
            "26 6 30 3|29 8 16 1|15 2 25 13|19 37 11 27|18 10 34 28|12 31 38 17|9 20 39 5|33 14 22 35|21 24 23 32|36 4 _ 7",
            "30 19 22 7 5 28 33|40 20 41 8 37 24 10|6 14 27 9 23 32 12|36 15 21 25 11 13 38|39 35 2 34 16 29 31|17 4 3 18 26 1 _",
            "29 39 25 18 8 26 20|34 3 9 17 13 4 30|16 35 1 7 10 27 22|15 38 41 19 33 40 23|36 5 12 _ 32 24 2|31 11 6 14 21 28 37",
            "2 10 31 37 33 40|30 20 12 6 3 5|26 34 27 23 22 8|19 16 11 9 17 32|_ 24 28 1 35 36|15 18 25 13 4 7|38 21 14 41 39 29",
            "23 11 20 41 18 40|29 34 9 5 26 21|39 32 38 2 13 22|24 17 28 30 7 35|3 19 6 33 15 36|37 27 _ 12 8 1|14 25 31 16 10 4",
            "41 10 2 18 23 43 12 31 14|1 36 13 19 37 9 28 16 24|33 35 40 6 17 29 5 38 42|20 27 21 25 _ 7 3 22 11|39 26 8 30 15 32 44 34 4",
            "41 9 32 22 16 3 35 2 6|14 37 11 38 1 24 10 27 18|40 30 17 12 23 39 21 43 19|20 7 4 26 13 34 36 28 25|31 8 5 33 44 15 29 _ 42",
            "35 15 2 19 26|36 25 14 32 21|43 12 31 23 29|38 27 28 20 7|9 10 22 4 _|1 33 11 17 16|44 39 3 24 18|5 40 37 13 30|42 8 34 41 6",
            "20 28 33 3 6|2 9 25 _ 12|27 43 32 37 26|15 13 17 38 5|14 30 34 35 41|4 1 39 8 44|18 31 11 40 36|23 16 24 21 29|7 10 22 19 42",
            "26 14 23 4 43 47 7 42|24 19 32 41 33 16 21 44|13 35 11 17 28 5 39 31|34 22 40 12 29 20 8 36|27 45 15 3 6 30 38 18|46 2 _ 37 10 1 9 25",
            "44 42 27 15 13 38 31 30|2 29 26 25 28 46 11 6|33 17 12 40 3 43 20 _|9 45 39 37 19 35 16 8|21 22 34 18 4 36 14 24|32 10 41 1 7 23 47 5",
            "25 9 35 2 1 23|43 45 8 11 7 5|17 6 19 4 14 46|22 40 42 47 13 12|10 29 21 38 _ 27|16 39 37 31 26 28|20 24 34 36 44 15|41 18 32 30 33 3",
            "27 7 45 _ 37 17|5 8 13 2 23 11|29 3 22 40 4 28|1 35 16 44 14 36|33 43 25 41 21 34|12 47 20 39 18 26|9 15 6 30 10 46|32 24 38 19 31 42",
            "11 14 24 39 42 5 47|10 46 27 2 45 28 41|12 29 7 38 3 48 35|16 36 37 44 23 32 31|34 15 26 _ 9 13 40|20 19 4 17 1 8 22|18 30 21 43 6 25 33",
            "33 17 8 29 45 3 36|7 2 15 18 30 24 12|22 5 35 32 20 23 4|38 19 48 47 39 16 28|44 11 21 42 41 1 9|13 46 14 43 _ 6 26|25 40 34 31 10 27 37",
            "6 29 10 4 26 20 17 18 41 16|3 12 2 9 15 40 14 24 1 36|11 35 34 22 21 28 33 13 43 23|8 37 5 38 42 30 45 49 _ 39|32 44 25 31 48 47 7 46 27 19",
            "11 1 25 37 14 48 42 21 43 34|33 13 26 29 24 49 40 35 18 6|9 2 5 31 45 30 38 32 22 8|36 41 39 47 23 _ 20 44 19 46|28 27 4 7 15 17 10 3 16 12",
            "2 15 5 40 33|17 39 25 32 30|13 36 4 1 27|3 35 19 6 24|49 7 29 38 42|28 37 45 11 31|10 26 8 22 41|14 20 23 21 46|43 12 9 44 47|16 48 18 34 _",
            "42 25 43 15 8|27 10 35 5 45|30 48 16 3 4|33 29 24 31 1|39 41 _ 26 11|46 7 40 47 13|14 49 12 37 23|9 34 44 19 22|17 28 21 18 32|6 2 38 20 36",
            "27 13 28 23 42 9 5 52 2|8 33 40 35 46 44 17 24 48|18 49 6 41 7 1 19 16 37|25 15 50 14 20 26 53 10 3|11 51 21 36 38 12 30 29 39|4 45 _ 32 43 47 22 31 34",
            "9 51 11 50 14 7 29 32 42|19 20 37 1 28 23 27 31 13|2 34 6 5 39 16 33 35 3|18 44 22 47 46 45 4 26 10|52 25 30 40 17 _ 41 43 36|53 24 49 8 38 21 48 15 12",
            "42 47 48 34 30 19|2 39 46 37 20 4|12 38 36 7 23 31|9 50 45 29 32 33|16 17 25 3 35 40|49 22 21 10 15 8|43 24 1 13 28 5|11 51 _ 41 6 44|52 14 53 27 18 26",
            "34 7 45 20 27 12|44 38 10 _ 15 9|26 2 28 29 21 3|14 39 23 5 25 33|46 1 16 36 52 13|22 35 49 53 30 32|18 47 51 43 40 24|4 41 17 31 11 50|42 19 8 6 48 37",
            "6 2 38 10 37 1 46 27|42 51 8 31 33 35 53 54|50 39 18 19 4 28 32 22|48 15 13 49 40 52 _ 36|5 21 24 9 11 14 44 29|43 26 23 3 55 12 16 41|34 45 20 7 17 47 25 30",
            "38 50 37 10 36 27 5 3|53 45 33 22 49 25 12 51|54 _ 55 18 19 16 1 44|29 8 39 2 17 34 13 23|41 35 15 42 47 48 40 11|30 26 46 24 31 14 32 7|52 9 43 20 21 28 6 4",
            "46 15 8 20 52 27 48|3 35 49 29 7 36 9|32 50 17 43 1 5 39|53 33 24 11 21 12 2|18 41 54 6 30 45 28|38 44 51 _ 22 40 23|10 47 14 42 4 34 31|25 55 26 37 16 13 19",
            "3 17 33 2 11 32 52|4 45 1 40 39 5 34|15 43 42 8 48 44 _|36 27 22 54 14 49 26|24 25 13 21 10 28 41|55 16 50 23 12 29 38|6 35 53 30 20 37 47|46 19 7 18 51 9 31",
            "34 24 37 9 38 42 41 5 35 16|23 13 15 31 17 11 29 36 25 39|26 55 20 49 14 45 6 43 33 8|30 4 48 50 53 _ 28 32 44 19|47 3 40 51 27 58 10 21 59 2|18 1 7 56 52 46 54 22 57 12",
            "38 54 53 36 6 52 5 33 40 45|49 35 11 1 42 3 _ 31 39 24|57 20 10 25 48 23 47 44 22 8|15 14 37 21 26 41 28 50 13 16|32 30 2 34 7 12 46 29 56 51|9 19 59 58 17 27 4 18 55 43",
            "37 3 40 30 39 53|1 23 34 16 9 5|21 36 33 22 32 27|35 13 24 11 8 17|59 15 47 _ 31 41|6 48 51 50 46 44|42 29 14 10 20 18|12 52 55 57 45 2|56 43 54 38 49 28|25 7 26 4 58 19",
            "35 42 53 20 33 34|27 4 25 13 38 45|5 44 41 40 58 32|26 55 49 17 10 39|8 18 3 52 57 11|51 16 2 36 19 46|12 47 14 6 28 1|43 7 54 _ 9 23|29 22 21 24 31 15|59 30 56 48 50 37",
            "31 1 4 44 53 29 15 51 19|16 34 52 21 5 7 43 27 12|41 46 8 33 50 26 62 60 32|28 37 24 57 59 17 40 47 55|20 6 58 25 42 _ 49 39 2|38 56 13 54 3 10 14 30 35|45 22 48 23 18 11 36 61 9",
            "49 35 56 14 53 18 51 3 47|29 33 52 1 58 32 12 6 20|46 36 17 2 60 4 19 48 62|5 23 11 41 42 39 22 28 55|44 61 _ 38 34 8 50 9 57|43 13 40 7 45 16 21 54 10|25 27 15 59 26 24 31 37 30",
            "7 25 12 10 32 62 37|30 33 14 8 54 52 48|29 55 2 18 26 58 5|56 4 43 15 45 9 51|24 3 47 40 35 31 1|13 23 46 61 28 19 49|50 53 57 59 36 34 41|20 11 _ 60 27 38 21|16 22 17 6 42 39 44",
            "28 7 44 31 13 10 61|15 49 22 12 48 46 37|9 2 29 33 30 58 14|24 41 39 36 57 3 56|43 27 53 20 32 40 _|34 21 47 17 38 62 23|50 6 16 8 4 52 35|42 11 5 55 1 51 19|60 45 54 59 25 26 18",
            "13 55 57 10 30 44 20 29|9 45 24 40 5 39 23 31|47 41 19 63 46 43 49 51|59 2 6 _ 33 34 38 53|56 8 3 52 50 4 21 27|32 35 14 22 15 28 61 18|48 12 26 54 58 42 11 1|7 25 36 37 16 62 60 17",
            "31 54 24 9 15 28 17 18|45 3 1 44 19 40 55 22|2 32 26 8 42 41 12 23|11 36 6 34 49 14 13 16|51 27 47 10 4 63 56 46|60 52 7 20 _ 35 39 43|25 5 37 29 48 30 33 62|58 53 21 57 38 61 59 50",
            "20 33 _ 65 26 51 54 40 13 46|64 14 38 21 22 5 29 59 63 52|67 28 56 3 16 37 1 48 62 53|35 50 45 34 68 12 27 4 60 69|15 2 42 9 23 41 30 36 47 66|39 11 44 55 57 19 6 58 8 10|18 24 49 7 25 32 43 31 61 17",
            "62 3 64 13 53 51 11 69 28 38|23 8 32 _ 18 46 49 52 31 34|56 66 4 30 27 68 48 54 17 39|59 58 63 61 65 16 33 50 7 60|55 67 36 22 5 10 20 15 2 47|19 12 45 14 57 43 42 26 29 41|25 21 24 1 44 37 6 35 9 40",
            "26 4 27 28 5 65 7|30 46 12 15 11 9 3|44 32 31 20 35 67 53|68 69 59 10 39 25 23|63 60 66 16 22 57 33|8 62 2 19 41 37 47|29 55 56 14 48 18 42|13 36 38 17 6 45 24|21 _ 58 51 43 1 50|52 61 54 34 64 40 49",
            "44 68 28 58 19 8 60|27 64 10 31 3 69 39|36 6 55 1 54 37 59|29 22 38 51 66 42 21|63 33 32 65 20 12 57|18 50 52 61 16 9 2|43 46 26 23 56 67 53|7 13 48 11 25 49 30|40 5 47 45 _ 62 4|17 41 35 24 14 15 34",
            "18 16 44 3 49 42 62 14 71|27 33 59 45 52 48 25 37 7|28 32 58 17 24 51 53 69 13|34 56 61 41 46 1 19 55 31|20 57 66 12 22 39 15 40 50|4 23 8 2 36 63 9 30 6|_ 68 21 11 70 38 60 10 5|65 47 67 54 64 26 43 29 35",
            "64 18 12 47 32 49 1 24 19|27 4 44 54 62 3 15 39 28|43 5 46 6 69 31 37 41 61|67 11 65 33 7 13 9 10 53|70 58 _ 71 50 20 14 66 17|40 59 63 21 55 23 26 36 51|22 68 35 60 29 16 2 48 38|56 52 8 42 25 30 57 34 45",
            "27 50 2 30 17 32 28 66|1 26 44 69 37 40 55 23|53 16 12 6 9 62 20 3|22 70 14 51 61 71 57 33|65 42 21 38 4 10 60 49|31 34 5 24 67 36 15 25|46 43 54 _ 8 56 18 13|63 29 48 52 47 7 19 68|45 59 41 35 11 64 58 39",
            "21 12 11 25 42 13 33 58|59 9 22 54 4 48 14 38|6 35 46 69 45 57 26 27|61 56 24 68 2 30 44 51|8 36 31 65 50 63 60 18|29 55 7 39 70 34 28 62|41 16 15 19 20 32 3 5|67 66 40 71 17 10 47 1|23 37 53 64 _ 49 43 52",
            "36 14 39 19 50 20 3 58 74 30|8 31 54 77 2 26 61 7 13 67|45 53 4 65 78 69 57 71 76 18|64 55 59 43 68 5 34 44 75 16|11 28 40 6 15 10 17 22 72 24|66 37 25 73 33 9 46 12 51 _|56 32 38 29 52 1 60 63 47 27|21 23 70 49 62 48 41 42 79 35",
            "41 40 34 70 16 6 57 50 49 28|3 19 36 60 24 43 10 15 44 68|30 35 74 73 53 65 9 79 1 20|47 7 54 13 27 63 66 18 11 21|14 52 _ 76 38 48 26 75 67 31|39 62 5 42 23 37 61 8 32 64|58 78 51 56 22 45 71 2 72 29|77 4 55 59 17 46 69 12 33 25",
            "77 24 17 46 62 47 55 78|29 49 33 71 37 16 6 43|75 8 19 41 70 59 42 64|74 36 76 32 28 23 9 45|7 22 26 14 51 20 50 30|57 58 52 48 54 68 _ 39|25 69 12 38 2 65 21 11|27 79 56 1 4 67 61 15|40 60 53 13 10 63 31 34|5 73 18 72 3 66 44 35",
            "43 59 38 68 27 44 6 23|31 36 3 42 71 7 57 52|14 66 76 55 64 25 18 13|1 29 50 39 77 11 37 28|9 20 47 67 74 2 73 78|24 63 60 19 40 53 34 10|46 22 61 21 48 35 62 5|16 33 65 72 26 32 8 69|58 4 15 70 51 12 _ 79|30 56 17 41 49 45 75 54",
            "70 38 23 56 10 6 42 55 80|37 20 15 64 51 47 77 3 46|27 30 35 7 28 74 48 25 24|34 50 75 78 9 _ 29 67 19|2 26 16 36 69 57 71 53 18|4 17 32 65 66 58 11 43 60|73 76 40 79 62 72 33 5 49|21 22 63 31 45 14 68 52 12|61 59 1 44 39 8 13 54 41",
            "13 66 67 4 2 26 18 72 60|65 20 30 46 43 39 23 8 9|3 24 58 14 47 61 80 41 29|37 27 48 21 16 11 15 1 5|74 22 69 12 34 57 45 75 25|33 _ 50 42 49 52 31 51 71|53 10 78 6 56 28 38 79 32|62 63 55 54 36 17 7 59 68|70 77 19 35 40 73 64 76 44",
            "85 80 44 40 77 83 50 51 48 55|14 18 74 69 _ 13 75 41 67 6|28 64 72 58 10 82 19 27 21 62|79 65 52 87 25 66 57 38 81 34|84 20 60 61 46 7 89 70 26 30|4 35 11 37 23 8 88 16 49 39|29 43 78 31 36 42 9 2 15 5|73 54 45 47 63 1 56 59 3 22|24 33 76 53 86 12 68 32 71 17",
            "31 15 32 9 39 50 74 19 76 10|73 18 42 16 28 7 4 43 61 49|45 89 48 60 65 84 47 70 24 57|53 67 1 54 86 20 87 69 72 88|22 23 66 40 14 77 41 37 34 12|38 6 3 56 79 46 52 58 25 30|36 64 2 68 17 80 59 29 85 27|78 33 8 75 71 5 55 21 82 62|63 83 _ 26 81 51 13 11 35 44",
            "24 30 72 15 84 71 46 86 7|78 77 8 79 75 61 63 28 82|67 13 66 51 83 33 68 69 16|14 89 38 _ 57 4 10 5 40|41 12 64 25 65 32 87 34 42|6 50 27 62 45 47 26 70 56|80 52 21 59 73 60 44 2 35|74 49 23 37 11 85 19 48 1|22 43 55 39 18 54 58 88 20|53 3 29 76 31 81 9 36 17",
            "22 2 27 68 84 39 76 25 30|34 17 85 79 47 11 5 59 71|75 66 37 44 67 54 57 55 29|83 38 28 _ 31 86 13 33 53|48 18 58 74 46 64 35 51 40|73 1 15 80 56 42 61 63 62|65 12 72 19 43 4 41 6 50|77 24 69 81 23 3 89 16 78|8 60 70 26 49 52 88 32 10|20 45 87 82 14 21 7 36 9",
            "57 49 38 56 30 83 18 93 47 17|43 68 54 58 48 36 7 46 13 87|79 61 19 74 6 62 11 34 37 3|98 66 35 76 73 84 27 44 71 31|65 60 97 99 20 14 90 22 67 69|92 78 51 2 25 41 59 24 15 26|39 40 88 95 55 52 94 5 63 8|82 77 53 45 86 42 16 85 72 70|1 80 4 23 10 50 29 12 9 75|_ 32 91 81 33 64 96 21 28 89",
            "65 43 89 1 67 40 95 79 87 44|59 41 20 92 30 17 96 97 34 22|26 13 56 27 84 35 99 70 23 48|62 47 61 16 86 21 64 51 94 74|12 63 91 28 98 82 66 4 8 _|11 81 37 50 45 78 39 7 69 77|71 68 93 85 75 54 33 38 18 60|83 32 6 5 90 31 19 73 72 15|29 25 36 46 14 3 52 24 53 10|42 9 76 57 88 55 58 2 80 49",
    };
}