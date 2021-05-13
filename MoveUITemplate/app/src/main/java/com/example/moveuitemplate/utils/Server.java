package com.example.moveuitemplate.utils;

public class Server {
    public static String localhost = "192.168.1.5";
    public static String urlAccount = "http://" + localhost + "/Server/getAccount.php";
    public static String urlSignUp = "http://" + localhost + "/Server/signUp.php";
    public static String urlLogin = "http://" + localhost + "/Server/userLogin.php";
    public static String ulrFavoriteFilm = "http://" + localhost + "/Server/favoriteFilm.php?id_user=";
    public static String urlDeleteFavoriteFilm = "http://"
            + localhost + "/Server/deleteFavoriteFilm.php";
    public static String returnUrlDeleteFavoriteFilm (String id_user, String id_film) {
        StringBuilder str = new StringBuilder();
        str.append("http://" + localhost + "/Server/deleteFavoriteFilm.php?");
        str.append("id=" + id_user);
        str.append("&id_film=" + id_film);
        return str.toString();
    }

}

