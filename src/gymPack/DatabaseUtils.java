package gymPack;

import java.sql.*;

public class DatabaseUtils {

    public static void insertBodyInformation(int weight, int height) {
        String getIDQuery = "SELECT MAX(ID) FROM dbo.body_information;";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbo?characterEncoding=latin1&useConfigs=maxPerformance", "root", "asdf1234");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(getIDQuery)) {

            int IDMax = 0;
            if (rs.next()) {
                IDMax = rs.getInt(1) + 1;
            }

            String insQuery = "INSERT INTO dbo.body_information (ID, WEIGHT, HEIGHT) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insQuery)) {
                pstmt.setInt(1, IDMax);
                pstmt.setInt(2, weight);
                pstmt.setInt(3, height);
                int rv = pstmt.executeUpdate();
                System.out.println("Rows affected: " + rv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static String getCurrentBMI() {
    	
    		String getBMIQuery = "SELECT BMI\r\n"
    				+ "FROM dbo.bmi\r\n"
    				+ "order by ID DESC\r\n"
    				+ "limit 1;";
    		
    		String currentBMI = null;
    	
			try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbo?characterEncoding=latin1&useConfigs=maxPerformance", "root", "asdf1234");
				Statement stmt = con.createStatement();
	    		ResultSet rs = stmt.executeQuery(getBMIQuery)) {
	    		
	    		if (rs.next()) {
	    			
	    			currentBMI = rs.getString(1);
	    			
	    		}
			}
	    		
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		return currentBMI;
    		
    }
}
