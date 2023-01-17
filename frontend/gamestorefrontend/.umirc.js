import { defineConfig } from "umi";

export default defineConfig({
  npmClient: 'yarn',
  proxy: {
    '/api': {
      'target': 'http://xxx:8080/',
      'changeOrigin': true,
      'pathRewrite': { '^/api' : '' },
    },
  }
});
