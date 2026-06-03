package presentation;

import business.MovieManagement;
import model.Movie;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MovieManagement movieManagement = new MovieManagement();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = inputInteger("Lựa chọn của bạn: ");
            try {
                switch (choice) {
                    case 1:
                        addMovie();
                        break;
                    case 2:
                        listMovies();
                        break;
                    case 3:
                        updateMovie();
                        break;
                    case 4:
                        deleteMovie();
                        break;
                    case 0:
                        System.out.println("Thoát chương trình!");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (SQLException e) {
                System.out.println("Lỗi thao tác cơ sở dữ liệu: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("====== MENU QUẢN LÝ PHIM ======");
        System.out.println("1. Thêm phim");
        System.out.println("2. Liệt kê phim");
        System.out.println("3. Sửa phim");
        System.out.println("4. Xóa phim");
        System.out.println("0. Thoát");
    }

    private static void addMovie() throws SQLException {
        String title = inputRequiredString("Nhập tiêu đề phim: ");
        String director = inputRequiredString("Nhập đạo diễn: ");
        int year = inputInteger("Nhập năm phát hành: ");
        movieManagement.addMovie(title, director, year);
        System.out.println("Thêm phim thành công!");
    }

    private static void listMovies() throws SQLException {
        List<Movie> movies = movieManagement.listMovies();
        if (movies.isEmpty()) {
            System.out.println("Danh sách phim trống!");
            return;
        }
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private static void updateMovie() throws SQLException {
        int id = inputInteger("Nhập ID phim cần sửa: ");
        String title = inputRequiredString("Nhập tiêu đề mới: ");
        String director = inputRequiredString("Nhập đạo diễn mới: ");
        int year = inputInteger("Nhập năm phát hành mới: ");
        movieManagement.updateMovie(id, title, director, year);
        System.out.println("Cập nhật phim thành công!");
    }

    private static void deleteMovie() throws SQLException {
        int id = inputInteger("Nhập ID phim cần xóa: ");
        movieManagement.deleteMovie(id);
        System.out.println("Xóa phim thành công!");
    }

    private static String inputRequiredString(String message) {
        System.out.print(message);
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Không được để trống dữ liệu bắt buộc!");
        }
        return value;
    }

    private static int inputInteger(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }
}
