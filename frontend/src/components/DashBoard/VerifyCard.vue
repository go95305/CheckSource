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
      <div class="block" @click.prevent="showModalOne = !showModalOne">
        <i class="hovicon effect-1 sub-a"
          ><span class="material-icons red500"> delete </span>
        </i>
      </div>

      <!-- modal -->
      <div class="customModal" v-if="showModalOne">
        <div class="customModalTitle">
          <button class="close" @click.prevent="showModalOne = !showModalOne">
            &times;
          </button>
          Modal One
        </div>
        <div class="customModalBody">
          <p>삭제할 프로젝트 명을 입력해주세요.</p>
        </div>
        <div class="customModalFooter">
          <button
            class="btn btn-primary"
            @click.prevent="showModalOne = !showModalOne"
          >
            Cancel
          </button>
        </div>
      </div>
      <!-- modal end -->

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

        <div class="verifycard-btn" @click="GoReport">
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
import '@/assets/css/DashBoard/VerifyCard.scss';

export default {
  name: 'VerifyCard',
  props: {
    project: Object,
  },
  data() {
    return {
      showModalOne: false,
    };
  },
  methods: {
    GoReport: function () {
      this.$emit('goReport', '26430578');
    },
  },
};
</script>
