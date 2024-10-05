// 부드러운 스크롤 기능
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();

        document.querySelector(this.getAttribute('href')).scrollIntoView({
            behavior: 'smooth'
        });
    });
});
// 네비게이션 바를 상단에 고정시키는 스크립트
window.onscroll = function() {stickyNav()};

// 네비게이션 바의 위치를 가져옴
var navbar = document.getElementById("navbar");
var sticky = navbar.offsetTop;

function stickyNav() {
    if (window.pageYOffset >= sticky) {
        navbar.classList.add("sticky");
    } else {
        navbar.classList.remove("sticky");
    }
}
function showContent(section) {
    // 모든 content 섹션을 숨기기
    const contents = document.querySelectorAll('.content');
    contents.forEach(content => content.style.display = 'none');

    // 선택한 섹션만 보이게 설정
    document.getElementById(section).style.display = 'block';
}
