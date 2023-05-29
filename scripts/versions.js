const versions = [
    "1.0.0",
    "0.18.0",
    "0.17.0",
    "0.16.0",
    "0.15.0",
    "0.14.0",
    "0.13.0",
    "0.12.0",
    "0.11.0",
    "0.10.0",
    "0.9.0",
    "0.8.0",
    "0.7.0",
    "0.6.0",
    "0.5.0",
    "0.4.0",
    "0.3.0",
    "0.2.0"
]
const latestVersion = versions[0];
const scriptTag = document.getElementById("versions-script");
const srcAttribute = scriptTag.getAttribute("src");
const versionPlaceholder = "Version"
var refPath;
var version;
if (srcAttribute.startsWith("..")) {
    refPath = scriptTag.getAttribute("src").match(/^(?:\.\.\/)+/)[0];
    version = scriptTag.dataset.version;
} else {
    refPath = "";
    version = versionPlaceholder;
}
if (version != versionPlaceholder && latestVersion != version) {
    window.addEventListener('load', () => {
        const rememberKey = `remember-outdated-${version}`
        const remember = sessionStorage.getItem(rememberKey);
        if (remember == null) {
            var outdatedWarningElement = document.getElementById("outdated-warning");
            if (outdatedWarningElement == null) {
                outdatedWarningElement  = document.createElement("div");
                outdatedWarningElement.id = "outdated-warning";
                const container = document.getElementById("container");
                if (container !== null) {
                    container.before(outdatedWarningElement);
                } else {
                    // for old dokka documentation
                    document.body.prepend(outdatedWarningElement);
                }
            }
            const closeAlertId = "outdated-warning__alert-close";
            outdatedWarningElement.innerHTML = `<div class="alert">
                    <span class="closebtn"><span class="hint">Don't show for the rest of the session &rarr; </span><span id="${closeAlertId}">&times;</span></span>
                    <span class="info-icon"></span><span class="info">Version ${version} is outdated, latest version is <a href="${refPath}${latestVersion}">${latestVersion}/</a>.</span>
                </div>`;
            const closeAlert = document.getElementById(closeAlertId);
            closeAlert.addEventListener('click', () => {
                sessionStorage.setItem(rememberKey, true);
                outdatedWarningElement.classList.remove("show");
            });
            outdatedWarningElement.classList.add("show");
        }
        const urlMatch = window.location.href.match(/\/[0-9]+\.[0-9]+\.[0-9]+\/(k?doc\/)?$/)
        if (urlMatch.size != 0) {
            const linkElement = document.createElement('link');
            linkElement.setAttribute('rel', 'canonical');
            linkElement.href = `${refPath}${latestVersion}/${urlMatch.size = 2 ? 'kdoc': ''}`;
            document.head.appendChild(linkElement);
        }
    });
}
const versionsElement = document.createElement("nav");
const versionsDropdownId = "versions__dropdown";

window.addEventListener('load', () => {
    versionsElement.id = "versions";
    versionsElement.tabIndex = -1;
    versionsElement.innerHTML = `
        <button tabindex="1" id="versions__button" type="button">${version}<span class="arrow"></span></button>
        <ul id="${versionsDropdownId}" class="dropdown" tabindex="-1">` +
            versions.filter(v => v != version).map((v, index) => `<li><a tabindex="${index + 2}" href="${refPath}${v}/">${v}</a></li>`).join('\n') +
        `</ul>`;
    const libraryNameDiv = document.getElementsByClassName("library-name")[0];
    libraryNameDiv.after(versionsElement);
    const versionsButton = document.getElementById("versions__button");
    versionsButton.addEventListener('click', () =>{
        toggleShowVersionList();
        event.stopPropagation();
    });

    versionsElement.addEventListener('keydown', event => navigateVersionListViaKeyboard(event));
});

function isVersionListOpen() {
    return versionsElement.classList.contains(showListClassName);
}

const showListClassName = 'showList';
function toggleShowVersionList(){
    if (isVersionListOpen()) {
        versionsElement.classList.remove(showListClassName)
        document.removeEventListener('keydown', closeVersionViaEscListener);
        document.removeEventListener("click", toggleShowVersionList);
    } else {
        versionsElement.classList.add(showListClassName);
        document.addEventListener('keydown', closeVersionViaEscListener);
        document.addEventListener("click", toggleShowVersionList);
    }
}

function closeVersionViaEscListener(event) {
    if (!event.isComposing && event.key === 'Escape') {
        toggleShowVersionList();
    }
}

function navigateVersionListViaKeyboard(event) {
    let targetToFocus = null;
    const isVersionOrButton =  event.target == versionsElement || event.target.tagName.toLowerCase() == "button"
    if (!event.isComposing && event.key == "ArrowUp" && !isVersionOrButton) {
        targetToFocus = event.target.parentElement.previousElementSibling?.firstChild;
    } else if (!event.isComposing && event.key == "ArrowDown") {
        if (isVersionOrButton) {
            if (!isVersionListOpen()) {
                toggleShowVersionList();
            }
            targetToFocus = document.getElementById(versionsDropdownId).firstElementChild.firstElementChild
        } else if(event.target.tagName.toLowerCase() == "a") {
            targetToFocus = event.target.parentElement.nextElementSibling?.firstElementChild;
        }
    } else if (!event.isComposing && event.key == "End") {
        targetToFocus = document.getElementById(versionsDropdownId).lastElementChild.firstElementChild;
    } else if (!event.isComposing && event.key == "Home") {
        targetToFocus = document.getElementById(versionsDropdownId).firstElementChild.firstElementChild;
     }

    if (targetToFocus != null) {
        targetToFocus.focus();
        targetToFocus.scrollIntoView({'block': 'center', 'behavior': 'smooth'});
        event.preventDefault();
    }
}