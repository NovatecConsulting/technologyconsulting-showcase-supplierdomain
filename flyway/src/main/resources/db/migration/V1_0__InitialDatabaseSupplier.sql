--
-- PostgreSQL database dump
--

-- Dumped from database version 11.9 (Debian 11.9-1.pgdg90+1)
-- Dumped by pg_dump version 11.9 (Debian 11.9-1.pgdg90+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: s_component; Type: TABLE; Schema: public; Owner: supplier_user
--

CREATE TABLE public.s_component (
    comp_id character varying(20) NOT NULL,
    container_size integer,
    comp_cost numeric(12,2),
    comp_desc character varying(100),
    lead_time integer,
    comp_name character varying(10),
    qty_demanded integer,
    qty_on_order integer,
    comp_site_id integer,
    comp_unit character varying(10),
    comp_version integer
);


ALTER TABLE public.s_component OWNER TO supplier_user;

--
-- Name: s_purchase_order; Type: TABLE; Schema: public; Owner: supplier_user
--

CREATE TABLE public.s_purchase_order (
    po_number integer NOT NULL,
    po_sent_date date,
    po_site_id integer NOT NULL,
    po_start_date timestamp without time zone,
    po_supp_id integer,
    po_version integer
);


ALTER TABLE public.s_purchase_order OWNER TO supplier_user;

--
-- Name: s_purchase_orderline; Type: TABLE; Schema: public; Owner: supplier_user
--

CREATE TABLE public.s_purchase_orderline (
    pol_number integer NOT NULL,
    pol_po_id integer NOT NULL,
    pol_location integer NOT NULL,
    pol_leadtime integer,
    pol_message character varying(100),
    pol_qty integer,
    pol_balance numeric(12,2),
    pol_p_id character varying(20),
    pol_deldate date,
    pol_version integer
);


ALTER TABLE public.s_purchase_orderline OWNER TO supplier_user;

--
-- Name: s_supp_component; Type: TABLE; Schema: public; Owner: supplier_user
--

CREATE TABLE public.s_supp_component (
    sc_p_id character varying(20) NOT NULL,
    sc_supp_id integer NOT NULL,
    sc_del_date integer,
    sc_discount numeric(38,0),
    sc_price numeric(12,2),
    sc_qty integer,
    sc_version integer
);


ALTER TABLE public.s_supp_component OWNER TO supplier_user;

--
-- Name: s_supplier; Type: TABLE; Schema: public; Owner: supplier_user
--

CREATE TABLE public.s_supplier (
    supp_id integer NOT NULL,
    supp_contact character varying(25),
    supp_name character varying(16),
    supp_reply_url character varying(128),
    supp_version integer,
    supp_ws_url character varying(128),
    supp_city character varying(20),
    supp_country character varying(10),
    supp_phone character varying(16),
    supp_state character varying(2),
    supp_street1 character varying(20),
    supp_street2 character varying(20),
    supp_zip character varying(9)
);


ALTER TABLE public.s_supplier OWNER TO supplier_user;

--
-- Name: sequence; Type: TABLE; Schema: public; Owner: supplier_user
--

CREATE TABLE public.sequence (
    seq_name character varying(50) NOT NULL,
    seq_count numeric(38,0)
);


ALTER TABLE public.sequence OWNER TO supplier_user;

--
-- Name: u_sequences; Type: TABLE; Schema: public; Owner: supplier_user
--

CREATE TABLE public.u_sequences (
    s_id character varying(50) NOT NULL,
    s_nextnum numeric(38,0)
);


ALTER TABLE public.u_sequences OWNER TO supplier_user;


--
-- Name: s_component s_component_pkey; Type: CONSTRAINT; Schema: public; Owner: supplier_user
--

ALTER TABLE ONLY public.s_component
    ADD CONSTRAINT s_component_pkey PRIMARY KEY (comp_id);


--
-- Name: s_purchase_order s_purchase_order_pkey; Type: CONSTRAINT; Schema: public; Owner: supplier_user
--

ALTER TABLE ONLY public.s_purchase_order
    ADD CONSTRAINT s_purchase_order_pkey PRIMARY KEY (po_number);


--
-- Name: s_purchase_orderline s_purchase_orderline_pkey; Type: CONSTRAINT; Schema: public; Owner: supplier_user
--

ALTER TABLE ONLY public.s_purchase_orderline
    ADD CONSTRAINT s_purchase_orderline_pkey PRIMARY KEY (pol_number, pol_po_id);


--
-- Name: s_supp_component s_supp_component_pkey; Type: CONSTRAINT; Schema: public; Owner: supplier_user
--

ALTER TABLE ONLY public.s_supp_component
    ADD CONSTRAINT s_supp_component_pkey PRIMARY KEY (sc_p_id, sc_supp_id);


--
-- Name: s_supplier s_supplier_pkey; Type: CONSTRAINT; Schema: public; Owner: supplier_user
--

ALTER TABLE ONLY public.s_supplier
    ADD CONSTRAINT s_supplier_pkey PRIMARY KEY (supp_id);


--
-- Name: sequence sequence_pkey; Type: CONSTRAINT; Schema: public; Owner: supplier_user
--

ALTER TABLE ONLY public.sequence
    ADD CONSTRAINT sequence_pkey PRIMARY KEY (seq_name);


--
-- Name: u_sequences u_sequences_pkey; Type: CONSTRAINT; Schema: public; Owner: supplier_user
--

ALTER TABLE ONLY public.u_sequences
    ADD CONSTRAINT u_sequences_pkey PRIMARY KEY (s_id);


--
-- Name: s_purchase_orderline fk_s_purchase_orderline_pol_po_id; Type: FK CONSTRAINT; Schema: public; Owner: supplier_user
--

ALTER TABLE ONLY public.s_purchase_orderline
    ADD CONSTRAINT fk_s_purchase_orderline_pol_po_id FOREIGN KEY (pol_po_id) REFERENCES public.s_purchase_order(po_number);

--
-- PostgreSQL database dump complete
--

