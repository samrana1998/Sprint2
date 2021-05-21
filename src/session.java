
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class session {
    public void insertUpdateDeleteStudent(char operation, int id, String Lec, String Tag, String Year, String Group, String Subject, String NoStudents, String Duration ){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
         if(operation == 'u'){
            try {
                ps = con.prepareStatement("UPDATE `session` SET  `Lec`= ?,`Tag`= ?,`Year`= ?,`Group`= ?,`Subject`= ?,`NoStudents`= ?,`Duration`= ? WHERE `id`= ?");
                
                ps.setString(1, Lec);
                ps.setString(2, Tag);
                ps.setString(3, Year);
                ps.setString(4, Group);
                ps.setString(5, Subject);
                ps.setString(6, NoStudents);
                ps.setString(7, Duration);
                ps.setInt(8, id);
                
                if(ps.executeUpdate()> 0 ){
                    JOptionPane.showMessageDialog(null, "Session Data Updated");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
         
         if(operation == 'i'){
            try {
                ps = con.prepareStatement("INSERT INTO session(id, Lec, Tag, Year, Group, Subject, NoStudents,  Duration) VALUES (?,?,?,?,?,?,?,?)");
                ps.setInt(1, id);
                ps.setString(2, Lec);
                ps.setString(3, Tag);
                ps.setString(4, Year);
                ps.setString(5, Group);
                ps.setString(6, Subject);
                ps.setString(7, NoStudents);
                ps.setString(8, Duration);
                
                
                if(ps.executeUpdate()> 0 ){
                    JOptionPane.showMessageDialog(null, "Session Data Inserted");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
         if(operation == 'd'){
            try {
                ps = con.prepareStatement("DELETE FROM `session` WHERE `id` = ?");
                
                
                ps.setInt(1, id);
                
                if(ps.executeUpdate()> 0 ){
                    JOptionPane.showMessageDialog(null, "Session Data Deleted");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
   
}
    }

    public void fillStudentJtable(JTable table, String valueToSearch){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `session` WHERE concat (`ID`,`Lec`,`Tag`,`Year`,`Group`,`Subject`,`NoStudents`,`Duration`) LIKE ?");
            ps.setString(1, "%"+valueToSearch+"%");
            
            ResultSet rs = ps.executeQuery();
           DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            while(rs.next()){
                
                
               row = new Object[8];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6]= rs.getString(7);
                row[7]= rs.getString(8);
               
                model.addRow(row);
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
