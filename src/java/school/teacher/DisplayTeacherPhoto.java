/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.teacher;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class DisplayTeacherPhoto extends HttpServlet {

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
            String teacherId = request.getParameter("teacher_id");
            Statement st = DBConnect.getConnection().createStatement();
            rs = st.executeQuery("SELECT teacher_photo FROM sch_teacher_info WHERE teacher_id='" + teacherId + "';");
            if (rs.next()) {
                byte[] bytearray = new byte[1048576];
                int size = 0;
                stdPhoto = rs.getBinaryStream("teacher_photo");
                response.reset();
                response.setContentType("image/jpeg");
                while ((size = stdPhoto.read(bytearray)) != -1) {
                    response.getOutputStream().write(bytearray, 0, size);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
