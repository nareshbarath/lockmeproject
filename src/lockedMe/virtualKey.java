package lockedMe;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class developerDetails {
	String name, email;

	// Setting developer details
	void setDeveloperDetails(String name, String email) {
		this.name = name;
		this.email = email;
	}

	// Displaying develper details
	void displayDeveloperDetails() {
		Scanner sc = new Scanner(System.in);
		System.out.println("DEVELOPER DETAILS\nNAME: " + name + "\nMAIL: " + email);
		System.out.println("\nPress enter any value and press enter to continue");
		sc.next();
	}
}

class mainMenu {
	static void displayMainMenu() throws IOException {
		Scanner sc = new Scanner(System.in);
		// Displaying the main menu
		System.out.println("Please enter a value from the given choices");
		System.out.println("1. Display all files in ascending order\n2. Go to file menu\n3. Exit");
		boolean run = true;
		do {
			try {
				int i = sc.nextInt(); // Get user's option
				switch (i) {
				case 1:
					fileOperations.displayFilesASC();
					System.exit(0);
					break;
				case 2:
					fileMenu.displayFileMennu();
					break;
				case 3:
					System.out.println("Program terminated");
					sc.close();
					System.exit(0);
					run = false;
					break;
				default:
					System.out.println("Invalid option please select again");
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Input missmatch please try again...");
				displayMainMenu();

			}
		} while (run == true); // Run till the program is terminated
	}
}

class fileMenu {
	public static void displayFileMennu() throws IOException {
		Scanner sc = new Scanner(System.in);
		// Displaying file operations menu
		System.out.println("Please enter a value from the given choices");
		System.out.println("1. Add a file\n2. Delete a file\n3. Search a file\n4. Return to previous menu\n5. Exit");
		int i;
		boolean run = true;
		do {
			try {
				i = sc.nextInt(); // Getting the user's choice
				switch (i) {
				case 1:
					fileOperations.addFile();
					break;
				case 2:
					fileOperations.deleteFile();
					break;
				case 3:
					fileOperations.searchFile();
					break;
				case 4:
					mainMenu.displayMainMenu();
					break;
				case 5:
					System.out.println("Progream terminated");
					System.exit(0);
					run = false;
					break;
				default:
					System.out.println("Invalid option please select again");
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Input missmatch please try again...");
				displayFileMennu();
			}
		} while (run == true);
	}
}

class fileOperations {
	public static void displayFilesASC() {
		String path = "lockmefiles"; // Folder path

		// Getting all files from the folder
		File directory = new File(path);
		File[] files = directory.listFiles();
		List<File> listofFiles = Arrays.asList(files);
		Collections.sort(listofFiles); // Sorting in ascending order

		if (files != null && files.length > 0) {
			System.out.println("Displaying files in ascending order...\n");
			for (File file : listofFiles) { // looping through the files in the folder
				System.out.println(file.getName()); // Displaying the file name
			}
			System.out.println("End of data...");
		} else {
			System.out.println("No files found");
		}
	}

	public static void addFile() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the file name to be created");
		String fileName = sc.next(); // Getting file name
		// Creating file in the folder directory
		Path filePath = Paths.get("./lockmefiles/" + fileName);
		Files.createDirectories(filePath.getParent()); // Creating the file

		System.out.println("File created successfully\nPlease end some data to be added to the file");
		String data = sc.next();
		// Adding data to file
		Files.write(filePath, data.getBytes());
		System.out.println("Data added into the file successfully");
		System.exit(0);
	}

	public static void deleteFile() {
		Scanner sc = new Scanner(System.in);
		displayFilesASC();
		System.out.println("\n\nPlease enter the name from the above list too be deleted...\n");
		String fileName = sc.next();
		File file = new File("lockmefiles", fileName);
		if (file.delete())
			System.out.println("The file has been deleted...");
		else
			System.out.println("File not found to be deleted...");
		System.exit(0);
	}

	public static void searchFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the name of the file that you want to search : ");
		String fileName = sc.next();

		// Getting all files from the folder
		File directory = new File("lockmefiles");
		File[] files = directory.listFiles();
		List<File> listofFiles = Arrays.asList(files);

		if (files != null && files.length > 0) {
			int i = 0;
			for (File file : listofFiles) {
				if (file.getName().startsWith(fileName)) { // If file matches then display
					System.out.println("File found : " + file.getName());
					i++;
				}
			}
			if (i == 0) {
				System.out.println("No files found with the searched name");
			}
			System.exit(0);
		} else {
			System.out.println("No files found");
			System.exit(0);
		}
	}
}

public class virtualKey {
	public static void main(String[] args) throws IOException {
		// Creating the folder to store files if it does not exist
		File file = new File("lockmefiles");
		if (!file.exists()) {
			file.mkdirs();
		}

		// Displaying developer details
		developerDetails dev = new developerDetails();
		dev.setDeveloperDetails("Naresh Barath V P", "nareshbarath2714@gmail.com");
		dev.displayDeveloperDetails();

		// Calling the main menu
		mainMenu.displayMainMenu();
	}
}
