import Cookies from "js-cookie";

const setCookie = (name, value, expires) => {
    Cookies.set(name , value, { expires: expires});
};

const getCookie = name => {
    return Cookies.get(name);
}

const deleteCookie = name => {
    return Cookies.remove(name);
};

export{ getCookie, setCookie, deleteCookie };