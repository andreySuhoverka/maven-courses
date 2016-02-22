package epam.sukhoverka.commands;


import epam.sukhoverka.dao.CourseDao;
import epam.sukhoverka.dao.LoginDAO;
import epam.sukhoverka.dao.UserDao;
import epam.sukhoverka.exception.system.NoSuchEntityException;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import epam.sukhoverka.manager.MessageManager;
import epam.sukhoverka.manager.PageManager;
import epam.sukhoverka.model.Course;
import epam.sukhoverka.model.Student;
import epam.sukhoverka.model.Teacher;
import epam.sukhoverka.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class LoginCommand implements ActionCommand {



        private final String PARAM_NAME_LOGIN = "login";
        private final String PARAM_NAME_PASSWORD = "password";
        private static Logger logger = Logger.getLogger(LoginCommand.class);


        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String page = null;
            String login = request.getParameter(PARAM_NAME_LOGIN);
            String pass = request.getParameter(PARAM_NAME_PASSWORD);

            LoginDAO loginDAO = new LoginDAO();
            UserDao userDao = new UserDao();
            CourseDao courseDao = new CourseDao();
            try {
                if (loginDAO.userExists(login, pass)) {

                    User user = userDao.createUserByLogin(login);
                    List<Course> currentCourses = courseDao.selectUserCoursesByStatus(login, "current");
                    request.getSession().setAttribute("currentCourses", currentCourses);

                    List<Course> pastCourses = courseDao.selectUserCoursesByStatus(login, "past");
                    request.getSession().setAttribute("pastCourses", pastCourses);

                    List<Course> upcomingCourses = courseDao.selectUserCoursesByStatus(login, "upcoming");
                    request.getSession().setAttribute("upcomingCourses", upcomingCourses);

                    if(user instanceof Student){
                        request.getSession().setAttribute("student", (Student)user);
                    }else if(user instanceof Teacher){
                        request.getSession().setAttribute("teacher", (Teacher)user);
                    }
                    page = PageManager.MAIN_PAGE_PATH;


                    //logger.info(login + " logged in system ( user = " +(user instanceof Teacher ? "teacher": "student") + " )");


                } else {
                    logger.info(login + " or " + pass + " incorrect");
                    request.setAttribute("errorMessage",
                            MessageManager.LOGIN_ERROR_MESSAGE.getMessage());
                    page = PageManager.LOGIN_PAGE_PATH;
                }
            }catch (NoSuchEntityException e) {
                e.printStackTrace();
                page = PageManager.ERROR_PAGE_PATH;
                //todo: вы ввели неправильный url сделать такую страницу
            } catch (DBSystemException e) {
                e.printStackTrace();
                /*request.setAttribute("errorMessage",
                        MessageManager.getProperty(MessageManager.DAO_ERROR_MESSAGE));*/
                page = PageManager.ERROR_PAGE_PATH;
            }
            return page;
        }
    }

