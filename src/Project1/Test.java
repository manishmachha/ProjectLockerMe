/*
  THIS PROGRAM IMPLEMENTS AN APPLICATION THAT CAN BE SIMPLY USED AS FILE EXPLORER
  CONTAINS OPTIONS SUCH AS : ADD FILE, DELETE FILE, SEARCH FILE, CHANGE DIRECTORY, SHOW FILES, CLOSE PROGRAM.
  @AUTHOR MANISH MACHHA
  @VERSION 1.0
  @DATE_CREATED 12/15/2021  9:38:PM
 */

package Project1;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class Test {
    static int inputOption;
    static String path = "C:\\Users\\manis\\Desktop\\Course 2 project\\TestCodeDirectory";

    /*DISPLAY 1ST SET OF OPTIONS*/
     static void FEoptions() throws IOException {
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("(1) Show the files");
        System.out.println("(2) Options");
        System.out.println("(3) Close the application");
        System.out.println("(4) Change Directory");
        System.out.print("Enter the Number to select the option : ");
        Scanner sc = new Scanner(System.in);
        inputOption = sc.nextInt();
        if (1 == inputOption) {
            showFiles();
        }
        if (2 == inputOption) {
            FEoptions2();
        }
        if (3 == inputOption) {
            CloseApp();
        }
        if (4 == inputOption) {
            changeDirectory();
        }
    }


    /*DISPLAY 2ND SET OF OPTIONS*/
    static void FEoptions1() throws IOException {
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("(1) Options");
        System.out.println("(2) Close the application");
        System.out.println("(3) Change Directory : ");
        System.out.print("Enter the Number to select the option : ");
        Scanner sc = new Scanner(System.in);
        inputOption = sc.nextInt();
        if (1 == inputOption) {
            FEoptions2();
        }
        if (2 == inputOption) {
            CloseApp();
        }
        if (3 == inputOption) {
            changeDirectory();
        }

    }


    /*DISPLAY 3RD SET OF OPTIONS*/
    static void FEoptions2() throws IOException {
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("(1) Add a file");
        System.out.println("(2) Delete a file");
        System.out.println("(3) Search a file");
        System.out.println("(4) Change Directory : ");
        System.out.println("(5) Close the application");
        System.out.print("Enter the Number to select the option : ");
        Scanner sc = new Scanner(System.in);
        inputOption = sc.nextInt();
        if (1 == inputOption) {
            AddFile();
        }
        if (2 == inputOption) {
            DelFile();
        }
        if (3 == inputOption) {
            searchFile();
        }
        if (4 == inputOption) {
            changeDirectory();
        }
        if (5 == inputOption) {
            CloseApp();
        }
    }


    /*METHOD TO CHANGE DIRECTORY*/
    static void changeDirectory() throws IOException {
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.print("Enter the Directory path : ");
        Scanner sc = new Scanner(System.in);
        path = sc.nextLine();
        if (path.contains("\"")) {
            String path1 = path.substring(1, (path.length() - 1));
            path = path1;
        }
        File file = new File(path);
        System.out.print("Changed Directory to : ");
        System.out.println(file.getCanonicalPath());
        FEoptions();

    }


    /*METHOD TO SHOW FILES*/
    static void showFiles() throws IOException {
        System.out.println("--------------------------------------------------------------------------------------------------------");
        File dir = new File(path);
        File[] files = dir.listFiles();
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));

        if (0 == files.length) {
            System.out.println("No Files Here");
            System.out.println("--------------------------------------------------------------------------------------------------------");
            FEoptions();
        } else {
            int FileNumCount = 1;
            for (File f : files) {
                System.out.println(FileNumCount + ")   --> " + f);
                FileNumCount++;
            }
            FEoptions1();
        }
    }


    /*METHOD TO ADD FILES*/
    static void AddFile() throws IOException {
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.print("Enter the file name with extension : ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        File file = new File(path + "\\\\" + fileName);
        if (search(fileName) == 0) {
            System.out.println("Error : Specified File already exists !");
            System.out.println("--------------------------------------------------------------------------------------------------------");
        } else {
            file.createNewFile();
            System.out.println("file created Successfully at " + file.getCanonicalPath());
            System.out.println("--------------------------------------------------------------------------------------------------------");
            FEoptions();
        }
    }


    /*METHOD TO DELETE FILES*/
    static void DelFile() throws IOException {
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.print("Enter the file name with extension you want to delete : ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        File file = new File(path + "\\\\" + fileName);
        if (search(fileName) == 0) {
            file.delete();
            System.out.println("File deleted successfully");
            System.out.println("--------------------------------------------------------------------------------------------------------");
            FEoptions();
        } else {
            System.out.println("Error : File Not Found !");
            System.out.println("--------------------------------------------------------------------------------------------------------");
            FEoptions();
        }
    }


    /*METHOD TO SEARCH FILES*/
    static void searchFile() throws IOException {
        System.out.println("--------------------------------------------------------------------------------------------------------");
        boolean found = false;
        System.out.print("Enter the file name with extension you want to search : ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        File file = new File(path);
        String[] files = file.list();
        int index = 0;
        for (int i = 0; i < files.length; i++) {
            if (fileName.equals(files[i])) {
                found = true;
                index = i;
            }
        }
        if (found) {
            System.out.println("File is found at : " + (index + 1));
            System.out.println("--------------------------------------------------------------------------------------------------------");

            FEoptions();
        } else {
            System.out.println("Error : File Not Found !");
            System.out.println("--------------------------------------------------------------------------------------------------------");
            FEoptions();
        }
    }


    /*METHOD TO CHECK SPECIFIED FILE IS PRESENT OR NOT*/
    static int search(String name) {
        boolean found = false;
        String fileName = name;
        File file = new File(path);
        String[] files = file.list();
        int index = 0;
        for (int i = 0; i < files.length; i++) {
            if (fileName.equals(files[i])) {
                found = true;
                index = i;
            }
        }
        if (found) {
            return 0;
        } else {
            return 1;
        }
    }


    /* METHOD TO EXIT PROGRAM*/
    static void CloseApp() {
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("Exited Application ");
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        String pathName = path;
        File file = new File(pathName);
        System.out.println("Application Name : FILE EXPLORER");
        System.out.println("Developer Name : MANISH MACHHA");
        System.out.println("Current Directory : " + file.getCanonicalPath());
        System.out.println("--------------------------------------------------------------------------------------------------------");
        String[] files = file.list();
        FEoptions();
    }
}
