window.addEventListener('load', () => {
    const remember = sessionStorage.getItem("remember-outdated-0.2.0")
    if (remember == null) {
        document.getElementById("outdated-warning").classList.add("show")
    }
});

function rememberOutdated(version) {
    sessionStorage.setItem(`remember-outdated-${version}`, true);
    document.getElementById("outdated-warning").classList.remove("show");
}
