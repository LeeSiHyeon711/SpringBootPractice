// 부드러운 스크롤 기능
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();

        var target = this.getAttribute('href');
        if (target !== '#') {  // 빈 #일 때는 실행하지 않음
            document.querySelector(target).scrollIntoView({
                behavior: 'smooth'
            });
        }
    });
});
document.addEventListener('DOMContentLoaded', function() {
    // URL에서 쿼리 파라미터 'section'을 가져오기
    const urlParams = new URLSearchParams(window.location.search);
    const section = urlParams.get('section');
    console.log("현재 섹션: ",section);
    // 섹션에 따라 해당 내용을 보여줌
    if (section) {
        showContent(section);  // 'posts', 'users', 'home' 중 하나를 showContent 함수로 전달
    } else {
        showContent('home');  // 기본적으로 공지사항을 표시
    }
});
function showContent(section) {
    // 모든 content 섹션을 숨기기
    const contents = document.querySelectorAll('.content');
    contents.forEach(content => content.style.display = 'none');

    // 선택한 섹션만 보이게 설정
    document.getElementById(section).style.display = 'block';
}
//관리자용 게시글 체크박스 전체 선택하기
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('selectAllBoards').onclick = function() {
        var checkboxes = document.getElementsByName('boardIds');
        for (var checkbox of checkboxes) {
            checkbox.checked = this.checked;
        }
    }
});
//관리자용 댓글 체크박스 전체 선택하기
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('selectAllComments').onclick = function() {
        var checkboxes = document.getElementsByName('commentId');
        for (var checkbox of checkboxes) {
            checkbox.checked = this.checked;
        }
    }
});
