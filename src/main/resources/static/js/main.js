/* ===== main.js — Global interactive behaviors ===== */
document.addEventListener('DOMContentLoaded', () => {

    /* ---------- Tab switching ---------- */
    document.querySelectorAll('[data-tabs]').forEach(wrapper => {
        const buttons = wrapper.querySelectorAll('.tab-btn');
        const panelContainer = document.querySelector(wrapper.dataset.tabs);
        if (!panelContainer) return;
        const panels = panelContainer.querySelectorAll('.tab-panel');

        buttons.forEach(btn => {
            btn.addEventListener('click', () => {
                const target = btn.dataset.target;
                buttons.forEach(b => b.classList.remove('active'));
                btn.classList.add('active');
                panels.forEach(p => {
                    p.classList.toggle('active', p.id === target);
                });
            });
        });
    });

    /* ---------- Mobile nav toggle ---------- */
    const menuToggle = document.getElementById('menuToggle');
    const mobileNav = document.getElementById('mobileNav');
    if (menuToggle && mobileNav) {
        menuToggle.addEventListener('click', () => {
            mobileNav.classList.toggle('open');
        });
    }

    /* ---------- Smooth scroll for anchor links ---------- */
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', e => {
            const targetId = anchor.getAttribute('href').slice(1);
            const targetEl = document.getElementById(targetId);
            if (targetEl) {
                e.preventDefault();
                targetEl.scrollIntoView({ behavior: 'smooth', block: 'start' });
            }
        });
    });

    /* ---------- Auto-dismiss alerts ---------- */
    document.querySelectorAll('.alert[data-dismiss]').forEach(alert => {
        const delay = parseInt(alert.dataset.dismiss) || 5000;
        setTimeout(() => {
            alert.style.transition = 'opacity 0.4s, transform 0.4s';
            alert.style.opacity = '0';
            alert.style.transform = 'translateY(-10px)';
            setTimeout(() => alert.remove(), 400);
        }, delay);
    });

    /* ---------- Number formatting helper ---------- */
    window.formatNumber = (num, decimals = 2) => {
        const n = parseFloat(num);
        return isFinite(n) ? n.toFixed(decimals) : '0.00';
    };

    /* ---------- Toast notification ---------- */
    window.showToast = (message, type = 'info') => {
        const toast = document.createElement('div');
        toast.className = `alert alert-${type}`;
        toast.style.cssText = 'position:fixed;top:20px;right:20px;z-index:9999;min-width:280px;animation:slideUp 0.4s ease';
        toast.textContent = message;
        document.body.appendChild(toast);
        setTimeout(() => {
            toast.style.opacity = '0';
            toast.style.transform = 'translateY(-10px)';
            setTimeout(() => toast.remove(), 400);
        }, 3500);
    };
});
