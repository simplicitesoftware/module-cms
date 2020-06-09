var CmsEditor = (function($) {
    function render(pagename) {
    	
		const editor = grapesjs.init({
		  container: '#gjs',
		  height: '100%',
		  width: 'auto',
		  storageManager: {
		    type: 'remote',
		    autosave: true,
		    autoload: true,
		    urlStore: "/ui/ext/CmsService?element="+pagename,
		    urlLoad: "/ui/ext/CmsService?service=grape&element="+pagename
		  },
		  cssIcons: null,
		  plugins: ['gjs-preset-webpage'],
	      pluginsOpts: {
	        'gjs-preset-webpage': {
	          // options
	        }
	      },
		  assetManager: {
		  	uploadFile:false,
		  	embedAdBase64: true
		  }
		});
    }
    
    return { render: render };
})(jQuery);
