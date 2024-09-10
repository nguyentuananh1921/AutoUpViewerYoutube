package tuanbuffet.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DcomManager {
    static String connectCommand = "rasdial \"nta\"";
    // Lệnh ngắt kết nối DCOM
    static String disconnectCommand = "rasdial \"nta\" /disconnect";
    public static void resetIp(){
        try {
            // Thực hiện lệnh kết nối
            executeCommand(connectCommand);

            // Thực hiện lệnh ngắt kết nối
            executeCommand(disconnectCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeCommand(String command) throws Exception {
        // Thực hiện lệnh hệ thống
        Process process = Runtime.getRuntime().exec(command);

        // Đọc đầu ra của lệnh
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // Đọc lỗi nếu có
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((line = errorReader.readLine()) != null) {
            System.err.println(line);
        }

        // Đợi cho đến khi lệnh hoàn thành
        process.waitFor();
        StringProcessing.sleep(2);
    }
}