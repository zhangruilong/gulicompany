package com.server.pojo.entity;

public class Givegoods {
    private String givegoodsid;

    private String givegoodscompany;

    private String givegoodscode;

    private String givegoodsname;

    private String givegoodsdetail;

    private String givegoodsunits;

    private String givegoodsunit;

    private Float givegoodsprice;

    private Integer givegoodsnum;

    private String givegoodsclass;

    private String givegoodsimage;

    private String givegoodsstatue;

    private String createtime;

    private String creator;
    
    private String givegoodsscope;

    private Company givegoodcompany;
    
    private Goodsclass giveGoodsClass;

    private Integer givegoodsseq;
    
	public String getGivegoodsscope() {
		return givegoodsscope;
	}

	public void setGivegoodsscope(String givegoodsscope) {
		this.givegoodsscope = givegoodsscope;
	}

	public Integer getGivegoodsseq() {
		return givegoodsseq;
	}

	public void setGivegoodsseq(Integer givegoodsseq) {
		this.givegoodsseq = givegoodsseq;
	}

	public Goodsclass getGiveGoodsClass() {
		return giveGoodsClass;
	}

	public void setGiveGoodsClass(Goodsclass giveGoodsClass) {
		this.giveGoodsClass = giveGoodsClass;
	}

	public Company getGivegoodcompany() {
		return givegoodcompany;
	}

	public void setGivegoodcompany(Company givegoodcompany) {
		this.givegoodcompany = givegoodcompany;
	}

	public String getGivegoodsid() {
        return givegoodsid;
    }

    public void setGivegoodsid(String givegoodsid) {
        this.givegoodsid = givegoodsid == null ? null : givegoodsid.trim();
    }

    public String getGivegoodscompany() {
        return givegoodscompany;
    }

    public void setGivegoodscompany(String givegoodscompany) {
        this.givegoodscompany = givegoodscompany == null ? null : givegoodscompany.trim();
    }

    public String getGivegoodscode() {
        return givegoodscode;
    }

    public void setGivegoodscode(String givegoodscode) {
        this.givegoodscode = givegoodscode == null ? null : givegoodscode.trim();
    }

    public String getGivegoodsname() {
        return givegoodsname;
    }

    public void setGivegoodsname(String givegoodsname) {
        this.givegoodsname = givegoodsname == null ? null : givegoodsname.trim();
    }

    public String getGivegoodsdetail() {
        return givegoodsdetail;
    }

    public void setGivegoodsdetail(String givegoodsdetail) {
        this.givegoodsdetail = givegoodsdetail == null ? null : givegoodsdetail.trim();
    }

    public String getGivegoodsunits() {
        return givegoodsunits;
    }

    public void setGivegoodsunits(String givegoodsunits) {
        this.givegoodsunits = givegoodsunits == null ? null : givegoodsunits.trim();
    }

    public String getGivegoodsunit() {
        return givegoodsunit;
    }

    public void setGivegoodsunit(String givegoodsunit) {
        this.givegoodsunit = givegoodsunit == null ? null : givegoodsunit.trim();
    }

    public Float getGivegoodsprice() {
		return givegoodsprice;
	}

	public void setGivegoodsprice(Float givegoodsprice) {
		this.givegoodsprice = givegoodsprice;
	}

	public Integer getGivegoodsnum() {
		return givegoodsnum;
	}

	public void setGivegoodsnum(Integer givegoodsnum) {
		this.givegoodsnum = givegoodsnum;
	}

	public String getGivegoodsclass() {
        return givegoodsclass;
    }

    public void setGivegoodsclass(String givegoodsclass) {
        this.givegoodsclass = givegoodsclass == null ? null : givegoodsclass.trim();
    }

    public String getGivegoodsimage() {
        return givegoodsimage;
    }

    public void setGivegoodsimage(String givegoodsimage) {
        this.givegoodsimage = givegoodsimage == null ? null : givegoodsimage.trim();
    }

    public String getGivegoodsstatue() {
        return givegoodsstatue;
    }

    public void setGivegoodsstatue(String givegoodsstatue) {
        this.givegoodsstatue = givegoodsstatue == null ? null : givegoodsstatue.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
}