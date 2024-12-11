// Chọn các phần tử
const themeToggleBtn = document.getElementById('themeToggle');
const body = document.body;
const navbar = document.getElementById('navbar');
const label = document.querySelector('.form-check-label');
const navLinks = document.querySelectorAll('.nav-link'); 

// Kiểm tra chế độ đã lưu trong localStorage
const savedTheme = localStorage.getItem('theme');
if (savedTheme === 'dark') {
    body.classList.add('dark-mode');
    navbar.classList.add('navbar-dark-mode');
    themeToggleBtn.checked = true;
    label.textContent = 'Light Mode';
} else {
    navbar.classList.add('navbar-light-mode');
    label.textContent = 'Dark Mode';
}

// Chuyển đổi giữa chế độ sáng và tối
themeToggleBtn.addEventListener('change', () => {
    body.classList.toggle('dark-mode');

    if (body.classList.contains('dark-mode')) {
        navbar.classList.remove('navbar-light-mode');
        navbar.classList.add('navbar-dark-mode');
        label.textContent = 'Light Mode';
        localStorage.setItem('theme', 'dark');
    } else {
        navbar.classList.remove('navbar-dark-mode');
        navbar.classList.add('navbar-light-mode');
        label.textContent = 'Dark Mode';
        localStorage.setItem('theme', 'light');
    }
});

const activePage = localStorage.getItem('activePage');
if (activePage) {
    navLinks.forEach(link => link.classList.remove('active'));
    const activeLink = document.querySelector(`.nav-link[href="${activePage}"]`);
    if (activeLink) {
        activeLink.classList.add('active');
    }
}

navLinks.forEach(link => {
    link.addEventListener('click', function () {
        navLinks.forEach(nav => nav.classList.remove('active'));
        this.classList.add('active');
        localStorage.setItem('activePage', this.getAttribute('href'));
    });
});

const emailForm = document.getElementById('emailForm');
const emailInput = document.getElementById('emailInput');
const emailError = document.getElementById('emailError');
const otpSection = document.getElementById('otpSection');
const generatedCodeInput = document.getElementById('generatedCode');
const copyCodeBtn = document.getElementById('copyCodeBtn');

// Hàm gọi API để lấy mã OTP từ backend
async function fetchOTP(email) {
    try {
        // Gửi yêu cầu tới backend để lấy file JSON (OTP code)
        const response = await fetch('/api/get-otp', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ email: email })
        });

        if (!response.ok) {
            throw new Error('Failed to fetch OTP');
        }

        const data = await response.json();
        return data.otp_code; // Giả sử file JSON trả về có trường otp_code
    } catch (error) {
        console.error('Error fetching OTP: ', error);
        alert('Failed to retrieve OTP code. Please try again.');
        return null;
    }
}


// Lấy các phần tử modal và theme toggle
const modal = document.getElementById('staticBackdrop');  
const themeToggle = document.getElementById('themeToggle'); 
const reportErrorBtn = document.getElementById('reportErrorBtn');  

// Kiểm tra xem có thời gian đếm ngược còn lại trong localStorage không
const lastClickedTime = localStorage.getItem('reportErrorBtnClickedTime');
const currentTime = new Date().getTime();
const cooldownPeriod = 60000;  

// Hàm cập nhật hiển thị thời gian đếm ngược trên nút
function updateCountdownDisplay(remainingTime) {
    const minutes = Math.floor(remainingTime / 60000);
    const seconds = Math.floor((remainingTime % 60000) / 1000);
    reportErrorBtn.textContent = `The system is fixing: ${minutes}:${seconds < 10 ? '0' + seconds : seconds}`;
}

// Kiểm tra nếu có thời gian đếm ngược còn lại trong localStorage
if (lastClickedTime) {
    const remainingTime = cooldownPeriod - (currentTime - lastClickedTime);
    if (remainingTime > 0) {
        reportErrorBtn.disabled = true;
        updateCountdownDisplay(remainingTime);
        const countdownInterval = setInterval(() => {
            const remainingTime = cooldownPeriod - (new Date().getTime() - lastClickedTime);
            if (remainingTime <= 0) {
                reportErrorBtn.disabled = false;
                reportErrorBtn.textContent = 'Report Error';  
                localStorage.removeItem('reportErrorBtnClickedTime'); 
                clearInterval(countdownInterval);
                document.getElementById('checkStatusBtn').click(); 
            } else {
                updateCountdownDisplay(remainingTime);
            }
        }, 1000);
    }
}

// Lắng nghe sự kiện khi người dùng mở modal (click vào "Report Error")
reportErrorBtn.addEventListener('click', () => {
    const userEmail = document.getElementById('emailInputReport').value;
    document.getElementById('userEmail').textContent = userEmail;  // Đưa email vào span#userEmail

    reportErrorBtn.disabled = true;
    localStorage.setItem('reportErrorBtnClickedTime', new Date().getTime());

    // Đếm ngược 1 phút (60,000 milliseconds)
    const countdownInterval = setInterval(() => {
        const remainingTime = cooldownPeriod - (new Date().getTime() - localStorage.getItem('reportErrorBtnClickedTime'));
        if (remainingTime <= 0) {
            reportErrorBtn.disabled = false;
            reportErrorBtn.textContent = 'Report Error';  // Reset button text
            localStorage.removeItem('reportErrorBtnClickedTime');  // Xoá thời gian đã lưu
            clearInterval(countdownInterval);

            // Tự động click nút Check Status khi hết thời gian
            document.getElementById('checkStatusBtn').click(); // Tự động click Check Status
        } else {
            updateCountdownDisplay(remainingTime);
        }
    }, 1000);

    // Kiểm tra trạng thái của chế độ và thay đổi lớp của modal
    if (themeToggle.checked) {
        // Chế độ tối
        modal.classList.add('modal-dark-mode');
        modal.classList.remove('modal-light-mode');
    } else {
        // Chế độ sáng
        modal.classList.add('modal-light-mode');
        modal.classList.remove('modal-dark-mode');
    }
});

// Lắng nghe sự kiện khi thay đổi chế độ trên theme toggle
themeToggle.addEventListener('change', () => {
    if (themeToggle.checked) {
        modal.classList.add('modal-dark-mode');
        modal.classList.remove('modal-light-mode');
    } else {
        modal.classList.add('modal-light-mode');
        modal.classList.remove('modal-dark-mode');
    }
});

// Lấy các phần tử cần thiết
const checkStatusBtn = document.getElementById('checkStatusBtn');
const statusMessage = document.getElementById('statusMessage'); // Ô thông báo trạng thái
const emailInputReport = document.getElementById('emailInputReport');

// Lắng nghe sự kiện khi người dùng nhấn "Check Status"
checkStatusBtn.addEventListener('click', (event) => {
    event.preventDefault();
    const userEmail = emailInputReport.value;

   
    if (userEmail && validateEmail(userEmail)) {
        // Hiển thị thông báo trạng thái
        statusMessage.style.display = 'block'; // Hiển thị ô thông báo
        statusMessage.classList.remove('alert-danger', 'alert-warning');
        statusMessage.classList.add('alert-info');
        statusMessage.textContent = `Checking status for email: ${userEmail}...`;

       
        setTimeout(() => {
            statusMessage.textContent = `Status for ${userEmail}: Active`;
            statusMessage.classList.remove('alert-info');
            statusMessage.classList.add('alert-success');
        }, 2000);
    } else {
        // Nếu email không hợp lệ, hiển thị lỗi
        statusMessage.style.display = 'block';
        statusMessage.classList.remove('alert-info', 'alert-success');
        statusMessage.classList.add('alert-danger');
        statusMessage.textContent = 'Invalid email. Please enter a valid email address.';
    }
});

// Hàm kiểm tra định dạng email
function validateEmail(email) {
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regex.test(email);
}

// Hàm áp dụng chế độ sáng/tối cho modal
function applyThemeToModal(modalElement, theme) {
    if (theme === 'dark') {
        modalElement.classList.add('modal-dark-mode');
        modalElement.classList.remove('modal-light-mode');
    } else {
        modalElement.classList.add('modal-light-mode');
        modalElement.classList.remove('modal-dark-mode');
    }
}

// Lắng nghe sự kiện khi trang tải
window.addEventListener('DOMContentLoaded', () => {
    const savedTheme = localStorage.getItem('theme') || 'light';
    const welcomeModalContent = document.getElementById('welcomeModalContent');

    // Áp dụng chế độ sáng/tối cho toàn bộ modal
    applyThemeToModal(welcomeModalContent, savedTheme);

    // Hiển thị modal Welcome
    const welcomeModal = new bootstrap.Modal(document.getElementById('welcomeModal'), {
        backdrop: 'static',
        keyboard: false,
    });
    welcomeModal.show();
});

// Lắng nghe sự kiện thay đổi chế độ
themeToggle.addEventListener('change', () => {
    const theme = themeToggle.checked ? 'dark' : 'light';

    // Cập nhật modal Welcome
    const welcomeModalContent = document.getElementById('welcomeModalContent');
    applyThemeToModal(welcomeModalContent, theme);
});






