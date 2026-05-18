/* ===== validation.js — Client-side input validation ===== */
document.addEventListener('DOMContentLoaded', () => {

    /** Utility: parse to number, return 0 if invalid */
    const toNum = v => { const n = parseFloat(v); return Number.isFinite(n) ? n : 0; };

    /** Show error message below input */
    const showError = (input, msg) => {
        clearError(input);
        input.classList.add('is-invalid');
        const err = document.createElement('div');
        err.className = 'form-error';
        err.textContent = msg;
        input.parentNode.appendChild(err);
    };

    /** Clear error from input */
    const clearError = (input) => {
        input.classList.remove('is-invalid');
        const existing = input.parentNode.querySelector('.form-error');
        if (existing) existing.remove();
    };

    /* ========== LOGIN FORM VALIDATION ========== */
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', e => {
            let valid = true;
            const cccd = document.getElementById('cccd');
            const birth = document.getElementById('birth');

            // Validate CCCD
            if (cccd) {
                clearError(cccd);
                const val = cccd.value.trim();
                if (!val) {
                    showError(cccd, 'Vui lòng nhập CCCD.');
                    valid = false;
                }
            }

            // Validate birth
            if (birth) {
                clearError(birth);
                const val = birth.value.trim();
                if (!val) {
                    showError(birth, 'Vui lòng nhập ngày sinh.');
                    valid = false;
                } else if (!/^\d{8}$/.test(val)) {
                    showError(birth, 'Ngày sinh phải đúng 8 chữ số (ddmmyyyy).');
                    valid = false;
                }
            }

            if (!valid) e.preventDefault();
        });
    }

    /* ========== ĐGNL CALCULATOR VALIDATION ========== */
    const dgnlScore = document.getElementById('dgnlScore');
    if (dgnlScore) {
        dgnlScore.addEventListener('input', () => {
            clearError(dgnlScore);
            const val = toNum(dgnlScore.value);
            if (val < 0 || val > 1200) {
                showError(dgnlScore, 'Điểm ĐGNL phải từ 0 đến 1200.');
            }
        });
    }

    /* ========== VSAT CALCULATOR VALIDATION ========== */
    const vsatFields = ['vsatSubject1', 'vsatSubject2', 'vsatSubject3'];
    vsatFields.forEach(fieldId => {
        const field = document.getElementById(fieldId);
        if (!field) return;
        field.addEventListener('input', () => {
            clearError(field);
            const scale = toNum(document.getElementById('vsatScale')?.value || 10);
            const max = scale === 150 ? 150 : 10;
            const val = toNum(field.value);
            if (val < 0 || val > max) {
                showError(field, `Điểm phải từ 0 đến ${max}.`);
            }
        });
    });

    /* Update max when scale changes */
    const vsatScale = document.getElementById('vsatScale');
    if (vsatScale) {
        vsatScale.addEventListener('change', () => {
            vsatFields.forEach(fieldId => {
                const field = document.getElementById(fieldId);
                if (field) clearError(field);
            });
        });
    }

    /* ========== ADMIN THRESHOLD VALIDATION ========== */
    document.querySelectorAll('.threshold-input').forEach(input => {
        input.addEventListener('input', () => {
            clearError(input);
            const val = toNum(input.value);
            if (val < 0 || val > 30) {
                showError(input, 'Điểm 0-30');
            }
        });
    });
});
