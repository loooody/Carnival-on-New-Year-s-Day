package com.store.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.entity.Category;
import com.store.entity.PageBean;
import com.store.entity.Product;
import com.store.service.impl.CategoryServiceImpl;
import com.store.service.impl.ProductServiceImpl;
import com.store.utils.UUIDUtils;
import com.store.utils.UploadUtils;

/**
 * 
 * 商品管理控制层
 * @author cb110
 *
 */




@Controller
@RequestMapping("adminProduct")
public class AdminProductController {
	
	
	@Autowired
	ProductServiceImpl productService;
	@Autowired
	CategoryServiceImpl categoryService;
	
	/**
	 * 
	 * 以分页的形式查询所有商品信息
	 * @param currPage
	 * @return
	 */
	@RequestMapping("findAllProductsWithPage")
	public ModelAndView findAllProductsWithPage(int currPage){
		int pageSize = 5;//每页显示记录数
		//调用业务层，以分页的形式查询所有商品信息
		PageBean<Product> pageBean = productService.findAllProductsWithPage(currPage,pageSize);
		return new ModelAndView("admin/product/list","page",pageBean);
	}
	
	/**
	 * 
	 * 分页查询上架商品
	 * @param currPage
	 * @return
	 */
	@RequestMapping("findAllOnsaleProductsWithPage")
	public ModelAndView findAllOnsaleProductsWithPage(int currPage){
		int pflag=0;
		int pageSize = 5;//每页显示记录数
		//调用业务层，以分页的形式查询所有商品信息
		PageBean<Product> pageBean = productService.findProductsByPflagWithPage(pflag, currPage, pageSize);
		return new ModelAndView("admin/product/list","page",pageBean);
	}
	
	/**
	 * 
	 * 分页查询下架商品
	 * @param currPage
	 * @return
	 */
	@RequestMapping("findAllUnsaleProductsWithPage")
	public ModelAndView findAllUnsaleProductsWithPage(int currPage){
		int pflag=1;
		int pageSize = 5;//每页显示记录数
		//调用业务层，以分页的形式查询所有商品信息
		PageBean<Product> pageBean = productService.findProductsByPflagWithPage(pflag, currPage, pageSize);
		return new ModelAndView("admin/product/pushDown_list","page",pageBean);
	}
	
	/**
	 * 
	 * 查询所有商品类别
	 * @return
	 */
	@RequestMapping("getAllCats")
	public ModelAndView addProductUI(){
        //查询分类信息
		List<Category> list = categoryService.getAllCats();
		return new ModelAndView("/admin/product/add","allCats", list);
	}
	
	/**
	 * 
	 * 商品添加
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addProduct")
	public ModelAndView addProduct(HttpServletRequest request) throws Exception{
		//存储表单中的数据
		Map<String, String> map = new HashMap<String,String>();
		//携带表单中的数据向service传递
		Product product = new Product();
		
		//利用request.getInputStream();获取到请求体中的全部数据，进行拆分和封装
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		List<FileItem> list = upload.parseRequest(request);
			
		//遍历集合
		for (FileItem item : list) {
			if(item.isFormField()) {
				//如果当前的FileItem对象是普通项
				//将普通项上的name属性值作为键，将获取到的内容作为值，放入map中
				map.put(item.getFieldName(), item.getString("utf-8"));
			}else {
				//如果当前的FileItem对象是上传项
				//获取到原始的文件名称
				String oldFileName = item.getName();
				//生成要保存的文件名称
				String newFileName = UploadUtils.getUUIDName(oldFileName);
					
				//通过FileItem获取到输入流对象，通过输入流可以获取到图片二进制数据
				InputStream is = item.getInputStream();
					
					
				//获取到当前项目下product/3下的真实路径
				String realPath = AdminProductController.class.getResource("/").getPath();
				int classPathnum=realPath.length();
				realPath=realPath.substring(1, classPathnum-16)+"/products/3";
				String dir = UploadUtils.getDir(newFileName);//生成随机目录
				String path = realPath + dir;
				//创建目录
				File newDir = new File(path);
				if(!newDir.exists()) {
					newDir.mkdirs();
				}
				//在服务端创建一个空文件
				File finalFile = new File(newDir,newFileName);
				if(!finalFile.exists()) {
					finalFile.createNewFile();
				}
					
				//建立和空文件对应的输出流
				OutputStream os = new FileOutputStream(finalFile);
				//将输入流中的数据刷入到输出流
				IOUtils.copy(is, os);
				//释放资源
				IOUtils.closeQuietly(is);
				IOUtils.closeQuietly(os);
					
				map.put("pimage", "/products/3"+dir+"/"+newFileName);
			}
		}
		//利用BeanUtils将map中的数据填充到product
		BeanUtils.populate(product, map);
			
		product.setPid(UUIDUtils.getId());
		product.setPdate(new Date());
		product.setPflag(0);
			
		//调用service将product上携带的数据存入数据库
		productService.saveProduct(product);
		
			
		//重定向到查询全部商品
		return new ModelAndView("redirect:findAllOnsaleProductsWithPage?currPage=1");
	}
	
	/**
	 * 
	 * 商品下架
	 * @param pid
	 * @return
	 */
	@RequestMapping("saletypetoun")
	public ModelAndView unsale(String pid){
		int pflag=1;
		productService.saletype(pflag,pid);
		//重定向到查询上架商品
		return new ModelAndView("redirect:findAllOnsaleProductsWithPage?currPage=1");
	}
	
	/**
	 * 
	 * 商品上架
	 * @param pid
	 * @return
	 */
	@RequestMapping("saletypetoon")
	public ModelAndView onsale(String pid){
		int pflag=0;
		productService.saletype(pflag,pid);
		//重定向到查询下架商品
		return new ModelAndView("redirect:findAllUnsaleProductsWithPage?currPage=1");
	}
	
	/**
	 * 
	 * 查询商品信息
	 * @param model
	 * @param pid
	 * @return
	 */
	@RequestMapping("editProductUI")
	public ModelAndView editProductUI(Model model ,String pid){
		//查询分类信息
		List<Category> list = categoryService.getAllCats();
		//查询商品信息
		Product product = productService.findProductByPid(pid);
		
		model.addAttribute("product", product);
		model.addAttribute("allCats", list);
		return new ModelAndView("/admin/product/edit");
	}
	
	/**
	 * 
	 * 修改商品信息
	 * @param request
	 * @param pimage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editProduct")
	public ModelAndView editProduct(HttpServletRequest request,String pimage)throws Exception{
		//System.out.println("pimage:" + pimage);
		
		Map<String, String> map = new HashMap<String,String>();
		//携带表单中的数据向service传递
		Product product = new Product();
		
		//利用request.getInputStream();获取到请求体中的全部数据，进行拆分和封装
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		List<FileItem> list = upload.parseRequest(request);
			
		//遍历集合
		for (FileItem item : list) {
			if(item.isFormField()) {
				//如果当前的FileItem对象是普通项
				//将普通项上的name属性值作为键，将获取到的内容作为值，放入map中
				map.put(item.getFieldName(), item.getString("utf-8"));
			}else {
				//如果当前的FileItem对象是上传项
				//获取到原始的文件名称
				String oldFileName = item.getName();
				//生成要保存的文件名称
				String newFileName = UploadUtils.getUUIDName(oldFileName);
					
				//通过FileItem获取到输入流对象，通过输入流可以获取到图片二进制数据
				InputStream is = item.getInputStream();
					
					
				//获取到当前项目下product/3下的真实路径
				String realPath = AdminProductController.class.getResource("/").getPath();
				int classPathnum=realPath.length();
				realPath=realPath.substring(1, classPathnum-16)+"/products/3";
				String dir = UploadUtils.getDir(newFileName);//生成随机目录
				String path = realPath + dir;
				//创建目录
				File newDir = new File(path);
				if(!newDir.exists()) {
					newDir.mkdirs();
				}
				//在服务端创建一个空文件
				File finalFile = new File(newDir,newFileName);
				if(!finalFile.exists()) {
					finalFile.createNewFile();
				}
					
				//建立和空文件对应的输出流
				OutputStream os = new FileOutputStream(finalFile);
				//将输入流中的数据刷入到输出流
				IOUtils.copy(is, os);
				//释放资源
				IOUtils.closeQuietly(is);
				IOUtils.closeQuietly(os);
					
				map.put("pimage", "/products/3"+dir+"/"+newFileName);
			}
		}
		//利用BeanUtils将map中的数据填充到product
		BeanUtils.populate(product, map);
		
		//调用service将product上携带的数据存入数据库
		if(product.getPimage().indexOf(".")==-1){
			product.setPimage(pimage);
		}
		productService.editProduct(product,product.getPid());
		//重定向到查询上架商品
		return new ModelAndView("redirect:findAllOnsaleProductsWithPage?currPage=1");
	}
	
	/**
	 * 
	 * 删除商品
	 * @param pid
	 * @return
	 */
	@RequestMapping("delProduct")
	public ModelAndView delProduct(String pid){
		productService.delProduct(pid);
		//重定向到查询下架商品
		return new ModelAndView("redirect:findAllUnsaleProductsWithPage?currPage=1");
	}
	
}
