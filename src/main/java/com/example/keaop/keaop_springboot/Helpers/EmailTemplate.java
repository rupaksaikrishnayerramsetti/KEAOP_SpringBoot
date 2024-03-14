package com.example.keaop.keaop_springboot.Helpers;

import org.springframework.stereotype.Service;

@Service
public class EmailTemplate {
    public String userAlertMsgTemplate(String title, String date, String time, String eventLink) {
        String subject = "EVENT REMINDER";
        String template = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<title>" + subject + "</title>" +
                "</head>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<table width='100%' cellpadding='10'>" +
                "<tr style='background-color: #007bff; color: #fff;'>" +
                "<td colspan='2' align='center'>" +
                "<h1>" + subject + " FOR " + title + "</h1>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td colspan='2'>" +
                "<h3>Dear User,</h3>" +
                "<p>We hope this message finds you well.</p>" +
                "<p>Your event details are as follows:</p>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Title:</strong></td>" +
                "<td><p>" + title + "</p></td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Date:</strong></td>" +
                "<td><p>" + date + "</p></td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Time:</strong></td>" +
                "<td><p>" + time + "</p></td>" +
                "</tr>" +
                "<tr>" +
                "<td colspan='2' style='background-color: #f2f2f2; padding: 5px;'><strong>Would you like to schedule this event in your calendar? </strong>" +
                "<a href='" + eventLink + "'>Click here to add into your Calendar</a>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td colspan='2' style='background-color: #f2f2f2; padding: 10px;'>" +
                "<p><strong>Alert:</strong> Don't forget about your upcoming event!</p>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td colspan='2' align='center'>" +
                "<p>Thank you for using our service. If you have any questions or need further assistance, please don't hesitate to contact us.</p>" +
                "<p>Best regards,</p>" +
                "<p>Your Service Provider</p>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "</body>" +
                "</html>";
        return template;
    }

    public String userCredentialsTemplate(String email, String pass) {
        String template = "<html>" +
                "<head>" +
                "<style>" +
                "body {" +
                "font-family: Arial, sans-serif;" +
                "background-color: #f2f2f2;" +
                "}" +
                ".container {" +
                "max-width: 600px;" +
                "margin: 0 auto;" +
                "padding: 20px;" +
                "background-color: #fff;" +
                "border-radius: 5px;" +
                "box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);" +
                "}" +
                ".header {" +
                "background-color: #007BFF;" +
                "color: #fff;" +
                "text-align: center;" +
                "padding: 10px;" +
                "border-radius: 5px 5px 0 0;" +
                "}" +
                ".content {" +
                "padding: 20px;" +
                "}" +
                ".button {" +
                "display: inline-block;" +
                "padding: 10px 20px;" +
                "background-color: #007BFF;" +
                "color: #fff;" +
                "text-decoration: none;" +
                "border-radius: 5px;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<div class='header'>" +
                "<h2>Welcome to Keep Everything at One Place Website</h2>" +
                "</div>" +
                "<div class='content'>" +
                "<p>Hello,</p>" +
                "<p>Your account has been created successfully.</p>" +
                "<p>Here are your login credentials:</p>" +
                "<p>Email: <strong>" + email + "</strong></p>" +
                "<p>Password: <strong>" + pass + "</strong></p>" +
                "<p>You can now log in using your email and password.</p>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";
        return template;
    }
    public String userCredentialsChangedTemplate(String email, String pass) {
        String template = "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f2f2f2;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: #fff;\n" +
                "            border-radius: 5px;\n" +
                "            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .header {\n" +
                "            background-color: #007BFF;\n" +
                "            color: #fff;\n" +
                "            text-align: center;\n" +
                "            padding: 10px;\n" +
                "            border-radius: 5px 5px 0 0;\n" +
                "        }\n" +
                "        .content {\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "        .button {\n" +
                "            display: inline-block;\n" +
                "            padding: 10px 20px;\n" +
                "            background-color: #007BFF;\n" +
                "            color: #fff;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <h2>Welcome to Keep Everything at One Place Website</h2>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <p>Hello,</p>\n" +
                "            <p>Your account login password was successfully Changed.</p>\n" +
                "            <p>Here are your login credentials:</p>\n" +
                "            <p>Email: <strong>" + email + "</strong></p>\n" +
                "            <p>Password: <strong>" + pass + "</strong></p>\n" +
                "            <p>You can now log in using your email and new password.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        return template;
    }

    public String userEditedAlertMsgTemplate(String title, String date, String time, String eventLink) {
        String subject = "UPDATED EVENT REMAINDER";
        String message = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>" + subject + "</title>\n" +
                "</head>\n" +
                "<body style='font-family: Arial, sans-serif;'>\n" +
                "    <table width='100%' cellpadding='10'>\n" +
                "        <tr style='background-color: #007bff; color: #fff;'>\n" +
                "            <td colspan='2' align='center'>\n" +
                "                <h1>" + subject + " FOR " + title + "</h1>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td colspan='2'>\n" +
                "                <h3>Dear User,</h3>\n" +
                "                <p>We hope this message finds you well.</p>\n" +
                "                <p>Your event details are as follows:</p>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td><strong>Title:</strong></td>\n" +
                "            <td>\n" +
                "                <p>" + title + "</p>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td><strong>Updated Date:</strong></td>\n" +
                "            <td>\n" +
                "                <p>" + date + "</p>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td><strong>Updated Time:</strong></td>\n" +
                "            <td>\n" +
                "                <p>" + time + "</p>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td colspan='2' style='background-color: #f2f2f2; padding: 5px;'><strong>Would you like to schedule this event in your calendar? </strong>\n" +
                "                <a href='" + eventLink + "'>Click here to add into your Calendar</a>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td colspan='2' style='background-color: #f2f2f2; padding: 10px;'>\n" +
                "                <p><strong>Alert:</strong> Don't forget about your upcoming event!</p>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td colspan='2' align='center'>\n" +
                "                <p>Thank you for using our service. If you have any questions or need further assistance, please don't hesitate to contact us.</p>\n" +
                "                <p>Best regards,</p>\n" +
                "                <p>Your Service Provider</p>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>\n" +
                "</html>";
        return message;
    }
}