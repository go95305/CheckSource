<template>
  <div>
    <my-project-path :department="'프로젝트'" />
    <h1 id="my-project-status-title">Gitlab</h1>
    <hr class="verify-header" />
    <div class="verify-body">
      <VerifyCard />
      <VerifyCard />
    </div>
  </div>
</template>

<script>
import '@/assets/css/MyProject/MyProjectGitlab.css';
import MyProjectPath from '../../components/MyProject/MyProjectPath.vue';
import VerifyCard from '../../components/DashBoard/VerifyCard.vue';

export default {
  name: 'MyProjectSummary',
  components: { MyProjectPath, VerifyCard },
  data() {
    return {};
  },
  methods: {},
};
</script>
