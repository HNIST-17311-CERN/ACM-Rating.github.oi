import { defineConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  base: "/ACM-Rating.github.oi/",
  title: "我的牛逼网站",
  description: "A VitePress Site",
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    nav: [
      { text: '首页', link: '/' },
      { text: 'ACM', link: '/markdown-examples' }
    ],

    sidebar: [
      {
        text: 'ACM',
        items: [
          { text: 'Markdown 示例', link: '/markdown-examples' },
          { text: 'Runtime API 示例', link: '/api-examples' },
          { text: 'rating', link: 'java/src/main/resources/ingrat'}
        ]
      }
    ],
 

    socialLinks: [
      { icon: 'github', link: 'https://github.com/vuejs/vitepress' }
    ]
  }
})
