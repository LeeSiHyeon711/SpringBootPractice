/*!
* Start Bootstrap - Simple Sidebar v6.0.3 (https://startbootstrap.com/template/simple-sidebar)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-simple-sidebar/blob/master/LICENSE)
*/
// 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {
    // URL에서 쿼리 파라미터 'section'을 가져오기
    const urlParams = new URLSearchParams(window.location.search);
    const section = urlParams.get('section') || 'home';  // 기본 섹션을 'home'으로 설정
    console.log("현재 섹션: ", section);

    // 섹션에 따라 해당 내용을 보여줌
    showContent(section);  // 'posts', 'users', 'home' 중 하나를 showContent 함수로 전달

    // 사이드바 메뉴 클릭 시 해당 섹션 표시
    const menuItems = document.querySelectorAll('.list-group-item');
    menuItems.forEach(item => {
        item.addEventListener('click', function(event) {
            event.preventDefault();  // 기본 동작 방지 (페이지 이동 방지)
            const targetSection = this.getAttribute('onclick').match(/showContent\('(.+?)'\)/)[1];
            showContent(targetSection);
        });
    });

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

    // 게시글 체크박스 전체 선택하기
    const selectAllBoards = document.getElementById('selectAllBoards');
    if (selectAllBoards) {
        selectAllBoards.onclick = function() {
            const checkboxes = document.getElementsByName('boardIds');
            checkboxes.forEach(checkbox => checkbox.checked = this.checked);
        };
    }

    // 댓글 체크박스 전체 선택하기
    const selectAllComments = document.getElementById('selectAllComments');
    if (selectAllComments) {
        selectAllComments.onclick = function() {
            const checkboxes = document.getElementsByName('commentId');
            checkboxes.forEach(checkbox => checkbox.checked = this.checked);
        };
    }

    // 섹션 보여주기 함수
    function showContent(section) {
        // 모든 content 섹션을 숨기기
        const contents = document.querySelectorAll('.content');
        contents.forEach(content => content.style.display = 'none');

        // 선택한 섹션만 보이게 설정
        const targetSection = document.getElementById(section);
        if (targetSection) {
            targetSection.style.display = 'block';
        } else {
            console.warn(`Section ${section} not found`);
        }
    }
});

