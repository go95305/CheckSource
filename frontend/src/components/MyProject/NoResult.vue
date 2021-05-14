<template>
    <div class="no-result">
        <img
            class="no-result-image"
            src="@/assets/images/sol_3.png"
            alt="no-result"
        />
        <div class="no-result-div">
            <span>결과를 찾을 수 없습니다.</span>
        </div>
    </div>
</template>
<script>
export default {
    name: "NoResult",
    props: {
        link: { type: String, default: "/myproject/scm/gitlab" },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/NoResult.css"></style>
