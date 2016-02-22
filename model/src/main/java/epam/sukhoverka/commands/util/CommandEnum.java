package epam.sukhoverka.commands.util;


import epam.sukhoverka.commands.*;

public enum CommandEnum {
    // мы не можем создать персональный конструктор для каждого объекта инама, по этому используем динамич блоки инициализации
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGIN_PAGE {
        {
            this.command = new LoginPageCommand();
        }
    },
    SIGN_UP_PAGE {
        {
            this.command = new SignUpPageCommand();
        }
    },
    MAIN_PAGE {
        {
            this.command = new MainPageCommand();
        }
    },
    DISPLAY_COURSE {
        {
            this.command = new DisplayCourseCommand();
        }
    },
    DISPLAY_ALL_COURSES {
        {
            this.command = new DisplayAllCoursesCommand();
        }
    },
    ENROLL_COURSE {
        {
            this.command = new EnrollCourseCommand();
        }
    },
    UN_ENROLL_COURSE {
        {
            this.command = new UnEnrollCourseCommand();
        }
    },
    ASSIGN_MARK {
        {
            this.command = new AssignMarkCommand();
        }

    },
    DISPLAY_STUDENTS_RATING{
        {
            this.command = new DisplayStudentsRating();
        }
    }, SIGN_OUT {
        {
            this.command = new SignOutCommand();
        }
    }, SIGN_UP {
        {
            this.command = new SignUpCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
