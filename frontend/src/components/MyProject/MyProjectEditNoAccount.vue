<template>
    <div class="no-result">
        <img
            class="no-result-image"
            src="@/assets/images/sol_3.png"
            alt="no-result"
        />
        <div class="no-result-div">
            <span>연결된 계정이 없습니다.</span>
            <router-link class="no-result-link" :to="link"
                >계정 연결하러 가기<span class="material-icons">
                    arrow_forward
                </span></router-link
            >
        </div>
    </div>
</template>
<script>
export default {
    name: "MyProjectEditNoAccount",
    props: {
        link: { type: String, default: "/myproject/scm/gitlab" },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/NoResult.css"></style>
