package com.example.gardenry;
public class Client {

        private String name;
        private String Pass;
        private String email;
        public Client() {
        }
        public Client(String u_name, String mails){

        }
        public Client(String uname, String Pass_1, String e_mail){
            name=uname;
            Pass=Pass_1;
            email=e_mail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPass() {
            return Pass;
        }

        public void setPass(String pass) {
            Pass = pass;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

