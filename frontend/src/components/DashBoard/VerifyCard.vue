<template>
    <div class="verifycard">
        <!-- left -->
        <div class="verifycard-aside">
            <div class="verifycard-aside-detail">
                <span class="verifycard-difficulty">12</span><br />
                <span class="verifycard-skill">Components</span>
            </div>

            <div class="verifycard-detail">
                <span class="verifycard-difficulty">5</span><br />
                <span class="verifycard-skill">Licenses</span>
            </div>
        </div>
        <!-- end of left -->

        <!-- right -->
        <div class="verifycard-main">
            <div class="verifycard-main-first">
                <p class="verifycard-excerpt">gitlab#1</p>
                <h3 class="verifycard-title">Project Name</h3>
            </div>

            <div class="verifycard-main-second">
                <div class="verifycard-skill-s">resolving</div>
            </div>

            <div class="verifycard-main-third">
                <div class="verifycard-excerpt">Last Scanned</div>
                <div class="verifycard-excerpt-s">김신한</div>
                <div class="verifycard-excerpt-s">2021.04.17</div>
            </div>

            <!-- down -->
            <div class="verifycard-meta">
                <div class="verifycard-btn">
                    <span class="material-icons"> replay </span>
                    <span class="vc-btn-detail">rescan</span>
                </div>

                <div class="verifycard-btn">
                    <span class="material-icons"> text_snippet </span>
                    <span class="vc-btn-detail">report</span>
                </div>
            </div>
            <!-- end of down -->
        </div>
        <!-- end of right -->
    </div>
</template>

<script>
export default {
    name: "VerifyCard",
    props: {
        project: Object,
    },
};
</script>
<style scoped src="@/assets/css/DashBoard/VerifyCard.css"></style>
