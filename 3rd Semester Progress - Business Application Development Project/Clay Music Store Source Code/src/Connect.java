

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Connect{

    public Statement stat;
    public ResultSet rs;
    public ResultSetMetaData rsm;
    public Connection con;
    public PreparedStatement pStat;
    
    public Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/claymusicstore","root", "" );
            stat = con.createStatement();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    
        
//Di Register        
    public void insertUser(String username, String email, String password, String gender, String role) {
         String query = "INSERT INTO users (username, email, password, gender, role) VALUES ('"+username+"','"+email+"','"+password+"','"+gender+"','"+role+"')";

            
         try {    
        	 stat.executeUpdate(query);  
        	 System.out.println("insert success");
          } catch (SQLException e) {
             System.out.println("insert failed");
             e.printStackTrace();
          }
    }
  
    
//Di Login    
    public ResultSet pStatLoginCheckAccount(String Email, String Password) {
    	String query = "SELECT * FROM users WHERE email = ? and password=?";
		
	  try {
		  pStat = con.prepareStatement(query);
		  pStat.setString(1, Email);
		  pStat.setString(2, Password);
		  
		  rs = pStat.executeQuery();
		  rsm = rs.getMetaData();

	  	} catch (Exception e) {
		// TODO: handle exception
	  	}
	  return rs;	  
  }
  
    
//Di manageMusicGenreForm    
    public void insertGenre(String genrename) {
        String query = "INSERT INTO music_genres (genre_name) VALUES (?)";
        
        try {
			pStat = con.prepareStatement(query);
			pStat.setString(1, genrename);
			
			pStat.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public void UpdateGenre(String AlterGenre, String value) {
    	try {
    		String query = "UPDATE music_genres SET genre_name = ? WHERE id =?";
    		pStat = con.prepareStatement(query);
			pStat.setString(1, AlterGenre);
			pStat.setString(2, value);
			pStat.executeUpdate();
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public void DeleteGenre(String value) {
    	try {			
			String queryDelete = "DELETE FROM music_genres WHERE id =?";
			pStat = con.prepareStatement(queryDelete);
			pStat.setString(1, value);
			pStat.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public ResultSet GetTableData() {
    	String query = "SELECT * FROM music_genres";
    	try {
			pStat = con.prepareStatement(query);
			rs = pStat.executeQuery();
			rsm = rs.getMetaData();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
    }
    
    
//Di ManageMusicForm
    public void UpdateMusic(String genreId, String MusicName, String MusicPrice, String ArtistName, String SelectedRowId) {
    	try {
    		String queryUpdate = "UPDATE musics SET music_genre_id=?, music_name=?, music_price=?,"
    				+ " music_artist_name=? WHERE id =?";
    		pStat = con.prepareStatement(queryUpdate);
			pStat.setString(1, genreId);
			pStat.setString(2, MusicName);
			pStat.setString(3, MusicPrice);
			pStat.setString(4, ArtistName);
			pStat.setString(5, SelectedRowId);
			pStat.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
    
    public void insertMusics(String musicgenreid, String musicname, String musicprice, String artistname, String releasedate) {
        try {
        	String query = "INSERT INTO musics(music_genre_id, music_name, music_price, music_artist_name, release_date) "
        			+ "VALUES(?,?,?,?,?)";
	
			pStat = con.prepareStatement(query);
			pStat.setString(1, musicgenreid);
			pStat.setString(2, musicname);
			pStat.setString(3, musicprice);
			pStat.setString(4, artistname);
			pStat.setString(5, releasedate);
			pStat.execute();
            System.out.println("insert success");
        } catch (SQLException b) {
            System.out.println("insert failed");
            b.printStackTrace();
        } 
        
 }  
    
	public void DeleteMusics(String Id) {
		try {
			String query = "DELETE FROM musics WHERE id =?";
			pStat = con.prepareStatement(query);
			pStat.setString(1, Id);
			pStat.execute();
		} catch (Exception e) {
			// TODO: handle exception
			}
	}
	
	public ResultSet GetGenreComboxData() {
		String queryGenreUpdate ="SELECT * FROM music_genres";
		try {
			pStat = con.prepareStatement(queryGenreUpdate);
			rs = pStat.executeQuery();
			rsm = rs.getMetaData();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	
	public ResultSet SelectGetMusicData() {
		String query = "SELECT m.id, m.music_artist_name, mg.genre_name, m.music_price, "
				+ "m.music_name, m.release_date FROM musics AS m JOIN music_genres "
				+ "AS mg ON m.music_genre_id = mg.id GROUP BY m.music_name ORDER BY m.id";
	try {
		pStat = con.prepareStatement(query);
		rs = pStat.executeQuery();
		rsm = rs.getMetaData();
	} catch (Exception e) {
		// TODO: handle exception
	}
	return rs;

}

//Di History
	public ResultSet SelectGetHistoDetailData(String Id) {
		String query ="SELECT hd.history_id, m.music_name, m.music_artist_name, m.music_price "
				+ "FROM  history_detail AS hd JOIN musics AS m ON hd.history_id = ?"
				+ "WHERE hd.music_id=m.id ";
		try {
			pStat = con.prepareStatement(query);
			pStat.setString(1, Id);
			rs = pStat.executeQuery();
			rsm = rs.getMetaData();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	
	public ResultSet getHistoryData() {
        String query = "SELECT * FROM history_header";

        try {
            pStat = con.prepareStatement(query);
            rs = pStat.executeQuery();
            rsm = rs.getMetaData();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return rs;
        
	}
//Di BuyMusicForm
	public ResultSet GetMusicData() {
        String query = "SELECT m.id, m.music_name, mg.genre_name, m.music_price, "
                + "m.music_artist_name, m.release_date FROM musics AS m JOIN music_genres "
                + "AS mg ON m.music_genre_id = mg.id GROUP BY m.music_name";
    try {
        pStat = con.prepareStatement(query);
        rs = pStat.executeQuery();
        rsm = rs.getMetaData();
    }catch (Exception e) {
        
    }
    return rs;
    }
	
	public void insertHistoryHeader(String user_id, int total_purchase) {
        try {
            String queryAddHistoryHeader = "INSERT INTO history_header(user_id, total_purchase, date_purchase)" 
                    + "VALUES (?, ?, CURRENT_DATE)";
            pStat = con.prepareStatement(queryAddHistoryHeader);
            pStat.setString(1, user_id);
            pStat.setInt(2, total_purchase);
            pStat.execute();
            System.out.println("insert success");
        } catch (Exception e) {

        }
        
    }   
    }
	
	
	
	
	