/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.student;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class DisplayStudentPhoto extends HttpServlet {

    private static final long serialVersionUID = 4593558495041379082L;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResultSet rs;
        InputStream stdPhoto;
        try {
            String studentId = request.getParameter("student_id");
            Statement st = DBConnect.getConnection().createStatement();
            rs = st.executeQuery("SELECT std_photo FROM sch_student_info WHERE std_id='" + studentId + "';");
            if (rs.next()) {
                byte[] bytearray = new byte[1048576];
                int size = 0;
                stdPhoto = rs.getBinaryStream("std_photo");
                response.reset();
                response.setContentType("image/jpeg");
                while ((size = stdPhoto.read(bytearray)) != -1) {
                    response.getOutputStream().write(bytearray, 0, size);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisplayStudentPhoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// </editor-fold>
    
}
